package com.bookstore.order.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    @Bean Queue orderEventQueue(){return new Queue("bookstore.order.events", true);}
}
