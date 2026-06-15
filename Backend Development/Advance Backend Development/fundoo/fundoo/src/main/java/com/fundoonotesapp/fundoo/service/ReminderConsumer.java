package com.fundoonotesapp.fundoo.service;

import com.fundoonotesapp.fundoo.config.RabbitMQConfig;
import com.fundoonotesapp.fundoo.dto.ReminderEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReminderConsumer {
    private final EmailService emailService;
    @RabbitListener(queues = RabbitMQConfig.REMINDER_QUEUE)
    public void consume(ReminderEmailDTO dto) {
        emailService.sendEmail(dto.getEmail(), dto.getSubject(), dto.getMessage());
    }
}