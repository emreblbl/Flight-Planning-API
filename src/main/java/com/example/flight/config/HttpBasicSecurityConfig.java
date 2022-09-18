package com.example.flight.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "securityType", havingValue = "http-basic")
public class HttpBasicSecurityConfig {
}
