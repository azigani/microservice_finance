package com.finpay.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimiterConfig {

    /**
     * Defines the RedisRateLimiter as a Spring Bean so that it is properly initialized
     */
    @Bean
    public RedisRateLimiter redisRateLimiter() {
        // 5 requests per second, burst capacity 10
        return new RedisRateLimiter(1, 10);
    }

    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> {
            String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                // simplest way: use the whole token string as key
                return Mono.just(authHeader.substring(7));
            }
            return Mono.just("anonymous");
        };
    }
}


