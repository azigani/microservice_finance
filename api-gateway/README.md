# Api Gateway
Folder Structures
```bash
api-gateway
  |_src
    |_main
      |_java
        |_com.finpay.gateway
          |_config
          |_controller
          |_ApiGatewayApplication.java
    |_resources
      |_application.yml
  |_pom.xml
  |_README.md
```
- [pom.xml](./pom.xml)
- [application.yml](./src/main/resources/application.yml)

Update [GatewayRoutesConfig](./src/main/java/com/finpay/gateway/config/GatewayRoutesConfig.java) for pointing to the microservices.

## Swagger API docs
- Add dependency
```xml
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-webflux</artifactId>
</dependency>

<dependency>
<groupId>org.springdoc</groupId>
<artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
<version>2.2.0</version>
</dependency>
```
- Update [application.yml](./src/main/resources/application.yml)
```bash
springdoc:
  swagger-ui:
    path: /swagger-ui.html
    urls:
      - url: /v3/api-docs/gateway
        name: api-gateway-docs
      - url: /v3/api-docs/auth
        name: auth-docs
      - url: /v3/api-docs/account
        name: account-docs
      - url: /v3/api-docs/transaction
        name: transaction-docs
    urls-primary-name: api-gateway-docs
    disable-swagger-default-url: true
    display-request-duration: true
    filter: true
    enabled: true 
```
- Update [GatewayRoutesConfig](./src/main/java/com/finpay/gateway/config/GatewayRoutesConfig.java)
```java
    // Swagger API docs routes Example
    .route("api-gateway-docs", r -> r.path("/v3/api-docs/gateway")
    .filters(f -> f.rewritePath("/v3/api-docs/gateway", "/v3/api-docs"))
    .uri("http://localhost:8080"))
```

```bash
mvn clean install
mvn spring-boot:run
```
Now hit: http://localhost:8080/webjars/swagger-ui/index.html

TODO:
- Add a Eureka Server module
```xml
<!-- Service Discovery (Eureka/Consul, optional) -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```
It will search based on service name.
```bash
application.yml

spring:
  application:
    name: auth-service
```
```java
.route("auth-service", r -> r.path("/auth-services/**")
        .uri("lb://auth-service"))
```
## Test Fallback Api
Stop transaction service
Then Call transaction APIs
```bash
http://127.0.0.1:8080/transactions/me
```
Expected:
```bash
Transaction Service is currently unavailable. Please try again later.
```
## Test RedisRateLimiter
In command line
```bash
for i in {1..20}; do 
  curl -i -H "Authorization: Bearer <your-jwt>" http://localhost:8080/frauds/transactions/{transactionId}
done
```
Expected:
First ~10 requests succeed (200 OK).
After tokens run out, you’ll start getting:
```bash
HTTP/1.1 429 Too Many Requests
```
Monitor Redis Keys
```bash
docker exec -it finpay-redis-1 redis-cli monitor
```