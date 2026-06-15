package com.fundoonotesapp.fundoo.service;

import com.fundoonotesapp.fundoo.dto.request.LabelRequest;
import com.fundoonotesapp.fundoo.dto.response.LabelResponse;

import java.util.List;

public interface LabelService {

    LabelResponse createLabel(LabelRequest request);

    List<LabelResponse> getLabels();

    LabelResponse updateLabel(Long labelId, LabelRequest request);

    void deleteLabel(Long labelId);

    void addLabelToNote(Long noteId, Long labelId);

    void removeLabelFromNote(Long noteId, Long labelId);
}