package com.fundoo.notificationservice.consumer;

import com.fundoo.notificationservice.config.RabbitMQConfig;
import com.fundoo.notificationservice.dto.ReminderEmailDto;
import com.fundoo.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQConsumer {

    private final EmailService emailService;

    @RabbitListener(
            queues = RabbitMQConfig.REMINDER_QUEUE
    )
    public void receiveReminder(ReminderEmailDto dto) {

        System.out.println("EMAIL = " + dto.getEmail());
        System.out.println("SUBJECT = " + dto.getSubject());
        System.out.println("BODY = " + dto.getBody());

        emailService.sendEmail(
                dto.getEmail(),
                dto.getSubject(),
                dto.getBody()
        );
    }
}