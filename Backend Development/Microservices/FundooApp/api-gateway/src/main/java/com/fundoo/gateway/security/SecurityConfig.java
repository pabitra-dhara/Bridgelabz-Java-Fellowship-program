package com.fundoo.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(cors -> {})
                .authorizeExchange(exchange -> exchange

                        .pathMatchers(
                                "/api-gateway/auth/**"
                        )
                        .permitAll()

                        .pathMatchers(
                                "/api/auth/**"
                        )
                        .permitAll()

                        .anyExchange()
                        .permitAll()
                )
                .build();
    }
}