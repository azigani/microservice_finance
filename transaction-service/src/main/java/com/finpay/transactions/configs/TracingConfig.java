package com.finpay.transactions.configs;

import io.micrometer.tracing.SamplerFunction;
import io.micrometer.tracing.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracingConfig {

    @Bean
    public SamplerFunction<Tracer> defaultSampler() {
        return SamplerFunction.alwaysSample();
    }
}
