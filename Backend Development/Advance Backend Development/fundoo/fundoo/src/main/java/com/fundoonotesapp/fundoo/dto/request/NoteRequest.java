package com.fundoonotesapp.fundoo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NoteRequest {

    @NotBlank
    private String title;

    private String description;
}
