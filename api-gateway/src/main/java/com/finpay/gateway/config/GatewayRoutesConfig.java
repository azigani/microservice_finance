package com.finpay.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.gateway.route.RouteLocator;

@Configuration
public class GatewayRoutesConfig {
    private final RedisRateLimiter redisRateLimiter;
    private final KeyResolver userKeyResolver;

    public GatewayRoutesConfig(RedisRateLimiter redisRateLimiter, KeyResolver userKeyResolver) {
        this.redisRateLimiter = redisRateLimiter;
        this.userKeyResolver = userKeyResolver;
    }
    @Bean
    public RouteLocator customRotes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/auth-services/**")
                        .uri("http://localhost:8081"))
                .route("account-service", r -> r.path("/accounts/**")
                        .uri("http://localhost:8082"))
                .route("transaction-service", r -> r.path("/transactions/**")
                        .filters(f -> f.circuitBreaker(c -> c
                                .setName("transactionCB")
                                .setFallbackUri("forward:/fallback/transactions")))
                        .uri("http://localhost:8083"))
                .route("notification-service", r -> r.path("/notifications/**")
                        .uri("http://localhost:8084"))
                .route("fraud-service", r -> r.path("/frauds/**")
                        .filters(f -> f
                                .requestRateLimiter(c -> {
                                    c.setRateLimiter(redisRateLimiter);
                                    c.setKeyResolver(userKeyResolver);
                                })
                        )
                        .uri("http://localhost:8085")
                )
                // Swagger API docs routes
                .route("api-gateway-docs", r -> r.path("/v3/api-docs/gateway")
                        .filters(f -> f.rewritePath("/v3/api-docs/gateway", "/v3/api-docs"))
                        .uri("http://localhost:8080"))
                .route("auth-docs", r -> r.path("/v3/api-docs/auth")
                        .filters(f -> f.rewritePath("/v3/api-docs/auth", "/v3/api-docs"))
                        .uri("http://localhost:8081"))
                .route("account-docs", r -> r.path("/v3/api-docs/account")
                        .filters(f -> f.rewritePath("/v3/api-docs/account", "/v3/api-docs"))
                        .uri("http://localhost:8082"))
                .route("transaction-docs", r -> r.path("/v3/api-docs/transaction")
                        .filters(f -> f.rewritePath("/v3/api-docs/transaction", "/v3/api-docs"))
                        .uri("http://localhost:8083"))
                .route("notification-docs", r -> r.path("/v3/api-docs/notification")
                        .filters(f -> f.rewritePath("/v3/api-docs/notification", "/v3/api-docs"))
                        .uri("http://localhost:8084"))
                .route("fraud-docs", r -> r.path("/v3/api-docs/fraud")
                        .filters(f -> f.rewritePath("/v3/api-docs/fraud", "/v3/api-docs"))
                        .uri("http://localhost:8085"))
                .build();
    }
}
