package com.fundoo.notificationservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReminderEmailDto {
    private String email;
    private String subject;
    private String body;
}