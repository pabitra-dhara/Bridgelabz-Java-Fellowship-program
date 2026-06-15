package com.fundoonotesapp.fundoo.service.impl;

import com.fundoonotesapp.fundoo.dto.request.ReminderRequest;
import com.fundoonotesapp.fundoo.dto.response.ReminderResponse;
import com.fundoonotesapp.fundoo.entity.Note;
import com.fundoonotesapp.fundoo.entity.Reminder;
import com.fundoonotesapp.fundoo.entity.User;
import com.fundoonotesapp.fundoo.repository.NoteRepository;
import com.fundoonotesapp.fundoo.repository.ReminderRepository;
import com.fundoonotesapp.fundoo.repository.UserRepository;
import com.fundoonotesapp.fundoo.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderServiceImpl implements ReminderService {

    private final ReminderRepository reminderRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    private User getLoggedInUser() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public ReminderResponse createReminder(Long noteId, ReminderRequest request) {
        User user = getLoggedInUser();
        Note note = noteRepository.findByIdAndUser(noteId, user).orElseThrow(() -> new RuntimeException("Note not found"));
        Reminder reminder = Reminder.builder().reminderTime(request.getReminderTime()).notified(false).note(note).build();
        reminderRepository.save(reminder);
        return ReminderResponse.builder().id(reminder.getId()).noteId(noteId).reminderTime(reminder.getReminderTime()).notified(false).build();
    }
    @Override
    public void deleteReminder(Long reminderId) {

        User user = getLoggedInUser();

        Reminder reminder =
                reminderRepository
                        .findByIdAndNoteUserId(
                                reminderId,
                                user.getId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Reminder not found"));

        reminderRepository.delete(reminder);
    }
    @Override
    public ReminderResponse updateReminder(
            Long reminderId,
            ReminderRequest request) {

        User user = getLoggedInUser();

        Reminder reminder =
                reminderRepository
                        .findByIdAndNoteUserId(
                                reminderId,
                                user.getId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Reminder not found"));

        reminder.setReminderTime(
                request.getReminderTime());

        reminder.setNotified(false);

        reminderRepository.save(reminder);

        return map(reminder);
    }
    private ReminderResponse map(Reminder reminder) {

        return ReminderResponse.builder()
                .id(reminder.getId())
                .noteId(reminder.getNote().getId())
                .reminderTime(reminder.getReminderTime())
                .notified(reminder.isNotified())
                .build();
    }
    @Override
    public List<ReminderResponse> getMyReminders() {

        User user = getLoggedInUser();

        return reminderRepository
                .findByNoteUserId(user.getId())
                .stream()
                .map(this::map)
                .toList();
    }
}