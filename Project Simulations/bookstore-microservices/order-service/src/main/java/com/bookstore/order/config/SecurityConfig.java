package com.bookstore.order.config;
import com.bookstore.security.*;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
public class SecurityConfig {
 @Bean SecurityFilterChain securityFilterChain(HttpSecurity http, JwtService jwt)throws Exception{
  http.csrf(c->c.disable()).cors(org.springframework.security.config.Customizer.withDefaults()).authorizeHttpRequests(a->a
   .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
   .requestMatchers("/swagger-ui/**","/swagger-ui.html","/v3/api-docs/**").permitAll()
   .anyRequest().authenticated())
   .addFilterBefore(new JwtAuthenticationFilter(jwt), UsernamePasswordAuthenticationFilter.class);
  return http.build();
 }
}
