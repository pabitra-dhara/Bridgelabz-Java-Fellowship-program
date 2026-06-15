package com.fundoonotesapp.fundoo.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReminderResponse {
    private Long id;
    private Long noteId;
    private LocalDateTime reminderTime;
    private boolean notified;
}