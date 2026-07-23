package com.fundoo.labelservice.service;



import com.fundoo.labelservice.dto.request.LabelRequest;
import com.fundoo.labelservice.dto.response.LabelResponse;

import java.util.List;

public interface LabelService {

    LabelResponse createLabel(LabelRequest request);

    List<LabelResponse> getLabels();

    LabelResponse updateLabel(Long labelId, LabelRequest request);

    void deleteLabel(Long labelId);

    LabelResponse getLabel(Long id);
}