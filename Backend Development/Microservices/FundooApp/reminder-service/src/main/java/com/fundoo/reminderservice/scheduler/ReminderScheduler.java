package com.fundoo.reminderservice.scheduler;

import com.fundoo.reminderservice.dto.ReminderEmailDto;
import com.fundoo.reminderservice.dto.UserResponse;
import com.fundoo.reminderservice.entity.Reminder;
import com.fundoo.reminderservice.repository.ReminderRepository;
import com.fundoo.reminderservice.service.RabbitMQProducer;
import com.fundoo.reminderservice.service.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {

    private final ReminderRepository reminderRepository;
    private final RabbitMQProducer rabbitMQProducer;
    private final UserServiceClient userServiceClient;

    @Scheduled(fixedRate = 60000)
    public void sendReminders() {

        List<Reminder> reminders =
                reminderRepository
                        .findByReminderTimeLessThanEqualAndNotifiedFalse(
                                LocalDateTime.now());

        for (Reminder reminder : reminders) {

            UserResponse user =
                    userServiceClient.getUserById(reminder.getUserId());

            ReminderEmailDto dto =
                    new ReminderEmailDto(
                            user.getEmail(),
                            "Reminder: " + reminder.getNoteTitle(),
                            reminder.getNoteDescription()
                    );

            rabbitMQProducer.sendReminder(dto);

            reminder.setNotified(true);
            reminderRepository.save(reminder);
        }
    }
}