package com.fundoonotesapp.fundoo.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LabelRequest {

    @NotBlank
    private String name;
}