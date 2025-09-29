# Account Service
Folder Structures
```bash
account-service
  |_src
    |_main
      |_java
        |_com.finpay.accounts
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
- And a simple security config [SecurityConfig](./src/main/java/com/finpay/accounts/securities/SecurityConfig.java)
- Add [CorsConfig](./src/main/java/com/finpay/accounts/configs/CorsConfig.java) to allow Other services to connect.
- Can Extract user info from JWT token [AccountController](./src/main/java/com/finpay/accounts/controllers/AccountController.java)
```java
// Extract JWT claims with @AuthenticationPrincipal
@GetMapping("/me")
public Map<String, Object> myAccount(@AuthenticationPrincipal Jwt jwt) {
    return Map.of(
            "user_id", jwt.getClaim("user_id"),
            "email", jwt.getClaim("email"),
            "roles", jwt.getClaim("scope")
    );
} 
```
- API End points
```bash
GET /accounts/me
POST /accounts
POST /accounts/debit
POST /accounts/credit
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
- Update [SecurityConfig](./src/main/java/com/finpay/accounts/securities/SecurityConfig.java)
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
For Authorization headers features in Swagger UI [OpenApiConfig](./src/main/java/com/finpay/accounts/securities/OpenApiConfig.java)

```bash
mvn clean install
mvn spring-boot:run
```
Now hit: http://localhost:8082/swagger-ui/index.html