package com.fundoonotesapp.fundoo.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReminderEmailDTO
        implements Serializable {

    private String email;

    private String subject;

    private String message;
}