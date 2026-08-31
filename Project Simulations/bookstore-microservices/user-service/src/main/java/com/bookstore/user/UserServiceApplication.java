package com.bookstore.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@ComponentScan({"com.bookstore.user", "com.bookstore.security"})
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
