package com.bookstore.notification;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessagingListener {

    @RabbitListener(queues = "bookstore.user.events")
    public void userEvent(String message) {
        System.out.println("RabbitMQ USER EVENT -> " + message);
    }

    @RabbitListener(queues = "bookstore.order.events")
    public void orderEvent(String message) {
        System.out.println("RabbitMQ ORDER EVENT -> " + message);
    }
}