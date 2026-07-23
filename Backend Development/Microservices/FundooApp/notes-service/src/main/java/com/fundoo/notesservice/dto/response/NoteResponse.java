package com.fundoo.notesservice.dto.response;

import lombok.Builder;
import lombok.Data;
import com.fundoo.notesservice.dto.response.LabelResponse;
import java.util.List;

@Data
@Builder
public class NoteResponse {

    private Long id;

    private String title;

    private String description;

    private boolean pinned;

    private boolean archived;

    private boolean trashed;

    private List<LabelResponse> labels;
}