package com.bookstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityBeans {
    @Bean
    public JwtService jwtService(org.springframework.core.env.Environment env) {
        return new JwtService(
                env.getProperty("jwt.secret",
                        "bookstore-secret-key-change-this-12345678901234567890"),
                env.getProperty("jwt.expiration-ms", Long.class, 86400000L)
        );
    }
}
