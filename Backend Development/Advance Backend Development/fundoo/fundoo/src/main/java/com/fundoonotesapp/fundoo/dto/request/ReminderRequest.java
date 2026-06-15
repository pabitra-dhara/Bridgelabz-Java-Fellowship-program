package com.fundoonotesapp.fundoo.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReminderRequest {
    private LocalDateTime reminderTime;
}