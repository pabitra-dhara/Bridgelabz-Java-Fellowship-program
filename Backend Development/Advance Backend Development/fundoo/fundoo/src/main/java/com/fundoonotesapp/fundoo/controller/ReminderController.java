package com.fundoonotesapp.fundoo.controller;

import com.fundoonotesapp.fundoo.dto.request.ReminderRequest;
import com.fundoonotesapp.fundoo.dto.response.ApiResponse;
import com.fundoonotesapp.fundoo.dto.response.ReminderResponse;
import com.fundoonotesapp.fundoo.service.ReminderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;

    @PostMapping("/{noteId}")
    public ApiResponse<ReminderResponse>
    createReminder(
            @PathVariable Long noteId,
            @RequestBody ReminderRequest request) {

        return new ApiResponse<>(
                "Reminder Created",
                reminderService
                        .createReminder(noteId,
                                request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String>
    deleteReminder(
            @PathVariable Long id) {

        reminderService.deleteReminder(id);

        return new ApiResponse<>(
                "Reminder Deleted",
                null);
    }

    @PutMapping("/{id}")
    public ApiResponse<ReminderResponse>
    updateReminder(
            @PathVariable Long id,
            @RequestBody ReminderRequest request) {

        return new ApiResponse<>(
                "Reminder Updated",
                reminderService.updateReminder(
                        id,
                        request));
    }
    @GetMapping
    public ApiResponse<List<ReminderResponse>>
    getAllReminders() {

        return new ApiResponse<>(
                "Success",
                reminderService.getMyReminders());
    }
}