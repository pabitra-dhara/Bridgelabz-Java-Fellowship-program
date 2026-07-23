package com.fundoo.reminderservice.controller;

import com.fundoo.reminderservice.dto.request.ReminderRequest;
import com.fundoo.reminderservice.dto.response.ApiResponse;
import com.fundoo.reminderservice.dto.response.ReminderResponse;
import com.fundoo.reminderservice.service.ReminderService;
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
            @RequestHeader("X-User-Email") String email,
            @PathVariable Long noteId,
            @RequestBody ReminderRequest request) {

        return new ApiResponse<>(
                "Reminder Created",
                reminderService.createReminder(
                        email,
                        noteId,
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
    getAllReminders(
            @RequestHeader("X-User-Email") String email) {

        return new ApiResponse<>(
                "Success",
                reminderService.getMyReminders(email));
    }
}