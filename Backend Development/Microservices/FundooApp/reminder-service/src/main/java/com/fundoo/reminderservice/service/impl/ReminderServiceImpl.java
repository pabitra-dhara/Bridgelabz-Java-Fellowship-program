package com.fundoo.reminderservice.service.impl;

import com.fundoo.reminderservice.dto.ReminderEmailDto;
import com.fundoo.reminderservice.dto.UserResponse;
import com.fundoo.reminderservice.dto.request.ReminderRequest;
import com.fundoo.reminderservice.dto.response.NoteResponse;
import com.fundoo.reminderservice.dto.response.ReminderResponse;
import com.fundoo.reminderservice.entity.Reminder;
import com.fundoo.reminderservice.repository.ReminderRepository;
import com.fundoo.reminderservice.service.NoteServiceClient;
import com.fundoo.reminderservice.service.RabbitMQProducer;
import com.fundoo.reminderservice.service.ReminderService;
import com.fundoo.reminderservice.service.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final RabbitMQProducer rabbitMQProducer;
    private final UserServiceClient userServiceClient;
    private final NoteServiceClient noteServiceClient;



    @Override
    public ReminderResponse createReminder(
            String email,
            Long noteId,
            ReminderRequest request) {

        UserResponse user =
                userServiceClient.getUserByEmail(email);

        NoteResponse note =
                noteServiceClient.getNote(noteId);

        Reminder reminder = Reminder.builder()
                .userId(user.getId())
                .noteId(noteId)
                .noteTitle(note.getTitle())
                .noteDescription(note.getDescription())
                .reminderTime(request.getReminderTime())
                .notified(false)
                .build();

        reminderRepository.save(reminder);
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
    getMyReminders(String email) {

        UserResponse user =
                userServiceClient.getUserByEmail(email);

        return reminderRepository
                .findByUserId(user.getId())
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