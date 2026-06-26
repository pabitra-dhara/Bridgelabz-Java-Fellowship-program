package com.fundoo.labelservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LabelRequest {

    @NotBlank
    private String name;
}