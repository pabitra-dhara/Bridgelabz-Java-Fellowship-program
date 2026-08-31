package com.bookstore.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@ComponentScan({"com.bookstore.book", "com.bookstore.security"})
public class BookServiceApplication {
    public static void main(String[] args){SpringApplication.run(BookServiceApplication.class,args);}
}

