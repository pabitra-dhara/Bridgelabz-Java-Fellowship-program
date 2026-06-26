package com.fundoo.reminderservice.service;

import com.fundoo.reminderservice.config.RabbitMQConfig;
import com.fundoo.reminderservice.dto.ReminderEmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendReminder(ReminderEmailDto dto) {

        rabbitTemplate.convertAndSend(
                "fundoo.reminder.queue",
                dto
        );
    }
}