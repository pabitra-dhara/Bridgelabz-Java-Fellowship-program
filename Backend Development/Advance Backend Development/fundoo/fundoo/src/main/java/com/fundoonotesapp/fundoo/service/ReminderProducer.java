package com.fundoonotesapp.fundoo.service;

import com.fundoonotesapp.fundoo.config.RabbitMQConfig;
import com.fundoonotesapp.fundoo.dto.ReminderEmailDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderProducer {
    private final RabbitTemplate rabbitTemplate;
    public void sendReminder(ReminderEmailDTO dto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.REMINDER_QUEUE, dto);
    }
}