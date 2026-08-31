package com.bookstore.user.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    Queue userEventQueue(){ return new Queue("bookstore.user.events", true); }
}
