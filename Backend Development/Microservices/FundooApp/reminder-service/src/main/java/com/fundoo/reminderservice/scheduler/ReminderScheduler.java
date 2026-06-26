package com.fundoo.reminderservice.scheduler;

import com.fundoo.reminderservice.dto.ReminderEmailDto;
import com.fundoo.reminderservice.entity.Reminder;
import com.fundoo.reminderservice.repository.ReminderRepository;
import com.fundoo.reminderservice.service.RabbitMQProducer;
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

    @Scheduled(fixedRate = 60000)
    public void sendReminders() {

        List<Reminder> reminders =
                reminderRepository
                        .findByReminderTimeLessThanEqualAndNotifiedFalse(
                                LocalDateTime.now());

        for (Reminder reminder : reminders) {

            ReminderEmailDto dto =
                    new ReminderEmailDto(
                            "pabitradhara096@gmail.com",
                            reminder.getNoteTitle(),
                            reminder.getNoteDescription()
                    );

            rabbitMQProducer.sendReminder(dto);

            reminder.setNotified(true);

            reminderRepository.save(reminder);
        }
    }
}