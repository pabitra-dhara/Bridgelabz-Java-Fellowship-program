package com.bookstore.book.config;

import com.bookstore.security.JwtAuthenticationFilter;
import com.bookstore.security.JwtService;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwt) throws Exception {
        http.csrf(c->c.disable())
            .authorizeHttpRequests(a->a
                .requestMatchers("/bookstore_user/get/book","/swagger-ui/**","/swagger-ui.html","/v3/api-docs/**").permitAll()
                .requestMatchers("/bookstore_user/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .addFilterBefore(new JwtAuthenticationFilter(jwt), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
