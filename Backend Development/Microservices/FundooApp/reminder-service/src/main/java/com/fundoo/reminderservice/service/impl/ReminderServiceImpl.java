package com.fundoo.reminderservice.service.impl;

import com.fundoo.reminderservice.dto.ReminderEmailDto;
import com.fundoo.reminderservice.dto.request.ReminderRequest;
import com.fundoo.reminderservice.dto.response.ReminderResponse;
import com.fundoo.reminderservice.entity.Reminder;
import com.fundoo.reminderservice.repository.ReminderRepository;
import com.fundoo.reminderservice.service.RabbitMQProducer;
import com.fundoo.reminderservice.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl
        implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final RabbitMQProducer rabbitMQProducer;

    private static final Long USER_ID = 1L;

    @Override
    public ReminderResponse createReminder(
            Long noteId,
            ReminderRequest request) {

        Reminder reminder =
                Reminder.builder()
                        .userId(USER_ID)
                        .noteId(noteId)
                        .reminderTime(request.getReminderTime())
                        .notified(false)
                        .build();

        reminderRepository.save(reminder);

        // Send message to RabbitMQ
        ReminderEmailDto dto =
                new ReminderEmailDto(
                        "pabitradhara096@gmail.com",
                        "Reminder Created",
                        "Your reminder has been created successfully."
                );

        rabbitMQProducer.sendReminder(dto);

        return map(reminder);
    }

    @Override
    public void deleteReminder(
            Long reminderId) {

        reminderRepository.deleteById(reminderId);
    }

    @Override
    public ReminderResponse updateReminder(
            Long reminderId,
            ReminderRequest request) {

        Reminder reminder =
                reminderRepository
                        .findById(reminderId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Reminder not found"));

        reminder.setReminderTime(
                request.getReminderTime());

        reminder.setNotified(false);

        reminderRepository.save(reminder);

        return map(reminder);
    }

    @Override
    public List<ReminderResponse>
    getMyReminders() {

        return reminderRepository
                .findByUserId(USER_ID)
                .stream()
                .map(this::map)
                .toList();
    }

    private ReminderResponse map(
            Reminder reminder) {

        return ReminderResponse.builder()
                .id(reminder.getId())
                .noteId(reminder.getNoteId())
                .reminderTime(
                        reminder.getReminderTime())
                .notified(
                        reminder.isNotified())
                .build();
    }
}