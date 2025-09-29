package com.finpay.common.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import io.micrometer.tracing.Tracer;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.io.IOException;

@Component
public class LoggingMdcFilter extends OncePerRequestFilter {

    private final Tracer tracer;

    @Autowired
    public LoggingMdcFilter(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // Trace/Span
            String traceId = tracer.currentSpan() != null ? tracer.currentSpan().context().traceId() : null;
            String spanId = tracer.currentSpan() != null ? tracer.currentSpan().context().spanId() : null;

            if (traceId != null) MDC.put("traceId", traceId);
            if (spanId != null) MDC.put("spanId", spanId);

            // Get Jwt from SecurityContextHolder
            Object principal = org.springframework.security.core.context.SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal();

            if (principal instanceof Jwt jwt) {
                MDC.put("userId", jwt.getClaimAsString("user_id"));
            } else if (principal instanceof JwtAuthenticationToken token) {
                MDC.put("userId", token.getToken().getClaimAsString("user_id"));
            }

            filterChain.doFilter(request, response);
        } finally {
            MDC.clear();
        }
    }
}
