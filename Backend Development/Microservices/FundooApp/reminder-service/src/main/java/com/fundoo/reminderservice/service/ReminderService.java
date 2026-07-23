package com.fundoo.reminderservice.service;

import com.fundoo.reminderservice.dto.request.ReminderRequest;
import com.fundoo.reminderservice.dto.response.ReminderResponse;

import java.util.List;

public interface ReminderService {

    ReminderResponse createReminder(
            String email,
            Long noteId,
            ReminderRequest request);

    void deleteReminder(Long reminderId);

    ReminderResponse updateReminder(
            Long reminderId,
            ReminderRequest request);

    List<ReminderResponse> getMyReminders(String email);
}