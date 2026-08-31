package com.bookstore.notification.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // User events queue
    @Bean
    public Queue userEventsQueue() {
        return new Queue("bookstore.user.events", true);
    }

    // Order events queue
    @Bean
    public Queue orderEventsQueue() {
        return new Queue("bookstore.order.events", true);
    }

    // JSON message converter
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}