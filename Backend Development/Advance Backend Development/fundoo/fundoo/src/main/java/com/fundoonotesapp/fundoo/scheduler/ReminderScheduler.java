package com.fundoonotesapp.fundoo.scheduler;

import com.fundoonotesapp.fundoo.dto.ReminderEmailDTO;
import com.fundoonotesapp.fundoo.entity.Reminder;
import com.fundoonotesapp.fundoo.repository.ReminderRepository;
import com.fundoonotesapp.fundoo.service.EmailService;
import com.fundoonotesapp.fundoo.service.ReminderProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {

    private final ReminderRepository reminderRepository;
    private final EmailService emailService;
    private final ReminderProducer producer;
    @Scheduled(fixedRate = 60000)
    public void processReminders() {

        List<Reminder> reminders =
                reminderRepository
                        .findByReminderTimeLessThanEqualAndNotifiedFalse(
                                LocalDateTime.now());

        for (Reminder reminder : reminders) {

            String email = reminder.getNote().getUser().getEmail();

            producer.sendReminder(ReminderEmailDTO.builder().email(email).subject("Fundoo Reminder").message(reminder.getNote().getTitle()).build());

            reminder.setNotified(true);

            reminderRepository.save(reminder);
        }
    }
}