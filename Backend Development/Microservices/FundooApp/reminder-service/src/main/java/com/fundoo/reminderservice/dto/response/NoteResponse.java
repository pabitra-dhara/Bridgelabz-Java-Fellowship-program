package com.fundoo.reminderservice.dto.response;

import lombok.Data;

@Data
public class NoteResponse {

    private Long id;

    private String title;

    private String description;
}
