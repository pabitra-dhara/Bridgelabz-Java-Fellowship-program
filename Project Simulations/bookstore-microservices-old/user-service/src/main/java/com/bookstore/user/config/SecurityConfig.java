package com.bookstore.user.config;

import com.bookstore.security.JwtAuthenticationFilter;
import com.bookstore.security.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwtService) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/bookstore_user/registration","/bookstore_user/login",
                                 "/bookstore_user/admin/registration","/bookstore_user/admin/login",
                                 "/bookstore_user/verification/**",
                                 "/swagger-ui/**","/swagger-ui.html","/v3/api-docs/**").permitAll()
                .anyRequest().authenticated())
            .addFilterBefore(new JwtAuthenticationFilter(jwtService),
                    UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }
}
