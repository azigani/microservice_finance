# Transaction Service
Folder Structures
```bash
transaction-service
  |_src
    |_main
      |_java
        |_com.finpay.transactions
          |_clients
          |_configs
          |_controllers
          |_models
          |_repositories
          |_securities
          |_services
          |_AccountServiceApplication.java
    |_resources
      |_keys
        |_public.pem
      |_application.properties
  |_pom.xml
  |_README.md
```
- [application.properties](./src/main/resources/application.yml)
Have to add this to verify JWT token from Auth service
```bash
spring
    security:
      oauth2:
        resourceserver:
          jwt:
            public-key-location: classpath:keys/public.pem 
```
- And a simple security config [SecurityConfig](src/main/java/com/finpay/transactions/securities/SecurityConfig.java)
- Add [CorsConfig](./src/main/java/com/finpay/transactions/configs/CorsConfig.java) to allow other services to connect.
## Add openfeign
To call Account service REST Api
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-openfeign-core</artifactId>
</dependency>
```
- Add [FeignConfig](./src/main/java/com/finpay/transactions/configs/FeignConfig.java) to add JWT token while calling to other services.
```java
@Bean
public RequestInterceptor requestInterceptor() {
    return (RequestTemplate template) -> {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            // Forward the Authorization header with the Bearer token
            template.header("Authorization", "Bearer " + jwt.getTokenValue());
        }
    };
}
```
## Swagger API docs
- Add dependency
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.2.0</version>
</dependency>
```
- Update [SecurityConfig](src/main/java/com/finpay/transactions/securities/SecurityConfig.java)
```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/swagger-ui.html"
                    ).permitAll()
                    .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

    return http.build();
} 
```
For Authorization headers features in Swagger UI [OpenApiConfig](src/main/java/com/finpay/transactions/securities/OpenApiConfig.java)

```bash
mvn clean install
mvn spring-boot:run
```
Now hit: http://localhost:8083/swagger-ui/index.html

# Add Logging, Tracing
- Update pom.xml
```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>

<!-- Zipkin reporter -->
<dependency>
    <groupId>io.zipkin.reporter2</groupId>
    <artifactId>zipkin-reporter-brave</artifactId>
</dependency>

```
## Tracing with Zipkin
Update docker-compose.yml to add zipkin service
```yml
  zipkin:
    image: openzipkin/zipkin
    container_name: zipkin
    ports:
      - "9411:9411"
```
- Update application.yml
```yml
management:
  tracing:
    sampling:
      probability: 1.0
    zipkin:
      base-url: http://localhost:9411
      enabled: true
```
- Add [TracingConfig](./src/main/java/com/finpay/transactions/configs/TracingConfig.java)
```java
@Configuration
public class TracingConfig {

    @Bean
    public SamplerFunction<Tracer> defaultSampler() {
        return SamplerFunction.alwaysSample();
    }
}
```
- Also Update common service pom.xml
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>

<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-oauth2-jose</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-core</artifactId>
</dependency> 
```
- Then add [LoggingMdcFilter](./../common/src/main/java/com/finpay/common/logging/LoggingMdcFilter.java)

## Kibana + Elasticsearch + Logstash
Update docker-compose.yml
```yml
      elasticsearch:
      image: docker.elastic.co/elasticsearch/elasticsearch:8.10.2
      container_name: elasticsearch
      environment:
        - discovery.type=single-node
        - xpack.security.enabled=false
        - ES_JAVA_OPTS=-Xms512m -Xmx512m
      ports:
        - "9200:9200"
        - "9300:9300"
      volumes:
        - elasticsearch_data:/usr/share/elasticsearch/data

      kibana:
        image: docker.elastic.co/kibana/kibana:8.10.2
        container_name: kibana
        environment:
          - ELASTICSEARCH_HOSTS=http://elasticsearch:9200
        ports:
          - "5601:5601"
        depends_on:
          - elasticsearch

      logstash:
        image: docker.elastic.co/logstash/logstash:8.10.2
        container_name: logstash
        ports:
          - "5001:5000"
        volumes:
          - ./logstash.conf:/usr/share/logstash/pipeline/logstash.conf
        depends_on:
          - elasticsearch
```
- Add logstash.conf in root folder where docker-compose.yml
```bash
input {
  tcp {
    port => 5000
    codec => json_lines
  }
}

output {
  elasticsearch {
    hosts => ["http://elasticsearch:9200"]
    index => "transaction-service-logs-%{+YYYY.MM.dd}"
  }
}

```
- Update application.yml
```yml
logging:
  pattern:
    level: "%5p [traceId=%X{traceId}, spanId=%X{spanId}, user=%X{userId}]"
```
- Add logback-spring.xml in src/main/resources
```xml
<configuration>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level [%thread] %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="LOGSTASH" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
        <destination>localhost:5001</destination>
        <encoder class="net.logstash.logback.encoder.LogstashEncoder" />
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="LOGSTASH"/>
    </root>

</configuration>

```
```bash
docker-compose up -d
mvn clean install
mvn spring-boot:run
```
- can see the list of indexes
```bash
http://localhost:9200/_cat/indices?v
```
Now hit: http://localhost:5601 to see logs in Kibana
Go to Stack Management -> Index Management -> Create index pattern
Put transaction-service-logs-* and click next step, then create index pattern.
Then go to Discover from left side panel, you will see the logs.
