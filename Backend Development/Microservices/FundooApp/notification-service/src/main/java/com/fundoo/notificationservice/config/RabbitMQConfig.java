package com.fundoo.notificationservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String REMINDER_QUEUE = "fundoo.reminder.queue";
    @Bean
    public Queue reminderQueue() {
        return new Queue(REMINDER_QUEUE, true
        );
    }
}