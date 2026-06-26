package com.fundoo.reminderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReminderEmailDto {

    private String email;
    private String subject;
    private String body;
}