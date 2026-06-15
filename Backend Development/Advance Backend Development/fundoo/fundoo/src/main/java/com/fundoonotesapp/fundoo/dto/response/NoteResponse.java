package com.fundoonotesapp.fundoo.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteResponse {

    private Long id;

    private String title;

    private String description;

    private boolean pinned;

    private boolean archived;

    private boolean trashed;
}