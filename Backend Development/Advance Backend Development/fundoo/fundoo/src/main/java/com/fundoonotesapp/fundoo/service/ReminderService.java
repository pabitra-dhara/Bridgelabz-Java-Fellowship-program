package com.fundoonotesapp.fundoo.service;

import com.fundoonotesapp.fundoo.dto.request.ReminderRequest;
import com.fundoonotesapp.fundoo.dto.response.ReminderResponse;

import java.util.List;

public interface ReminderService {
    ReminderResponse createReminder(Long noteId, ReminderRequest request);
    void deleteReminder(Long reminderId);
    ReminderResponse updateReminder(Long reminderId, ReminderRequest request);
    List<ReminderResponse> getMyReminders();
}
