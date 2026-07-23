package com.fundoo.notesservice.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabelResponse {

    private Long id;
    private String name;
}