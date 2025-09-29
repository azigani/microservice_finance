# Auth Service
Folder structures
```bash
auth-service
  |_src
    |_main
      |_java
        |_com.finpay.authservice
          |_configs
          |_controllers
          |_dataseeders
          |_exceptions
          |_models
          |_repositories
          |_securities
          |_services
          |_AuthServiceApplication.java
      |_resources
        |_keys
          |_private.pem
          |_public.pem
  |_pom.xml
  |_READEM.md
```
##### Create keys
Open CMD
```bash
cd auth-service/src/main/resources/keys
# private key (signing)
openssl genrsa -out private.pem 2048
# public key (verification)
openssl rsa -in private.pem -pubout -out public.pem
```
- [pom.xml](./pom.xml)
- [application.yml](./src/main/resources/application.yml)

- API End points
```bash
POST /auth-services/users
POST /auth-services/login
GET /auth-services/users
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
- Update [SecurityConfigConfiguration](./src/main/java/com/finpay/authservice/securities/SpringSecurityConfiguration.java)
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
For Authorization headers features in Swagger UI [OpenApiConfig](./src/main/java/com/finpay/authservice/securities/OpenApiConfig.java)

```bash
mvn clean install
mvn spring-boot:run
```
Now hit: http://localhost:8081/swagger-ui/index.html