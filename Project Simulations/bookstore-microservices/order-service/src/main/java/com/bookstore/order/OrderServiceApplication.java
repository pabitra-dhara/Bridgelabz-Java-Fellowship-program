package com.bookstore.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan({"com.bookstore.order", "com.bookstore.security"})
public class OrderServiceApplication {
    public static void main(String[] args){SpringApplication.run(OrderServiceApplication.class,args);}
}
