package com.fundoo.labelservice.service.impl;

import com.fundoo.labelservice.dto.request.LabelRequest;
import com.fundoo.labelservice.dto.response.LabelResponse;
import com.fundoo.labelservice.entity.Label;
import com.fundoo.labelservice.exception.LabelNotFoundException;
import com.fundoo.labelservice.repository.LabelRepository;
import com.fundoo.labelservice.service.LabelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabelServiceImpl implements LabelService {

    private final LabelRepository labelRepository;

    private static final Long USER_ID = 1L;

    @Override
    public LabelResponse createLabel(LabelRequest request) {

        Label label = Label.builder()
                .name(request.getName())
                .userId(USER_ID)
                .build();

        labelRepository.save(label);

        return map(label);
    }

    @Override
    public List<LabelResponse> getLabels() {

        return labelRepository.findByUserId(USER_ID)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public LabelResponse updateLabel(Long labelId,
                                     LabelRequest request) {

        Label label = labelRepository.findById(labelId)
                .orElseThrow(() ->
                        new LabelNotFoundException(
                                "Label not found"));

        label.setName(request.getName());

        labelRepository.save(label);

        return map(label);
    }

    @Override
    public void deleteLabel(Long labelId) {

        Label label = labelRepository.findById(labelId)
                .orElseThrow(() ->
                        new LabelNotFoundException(
                                "Label not found"));

        labelRepository.delete(label);
    }

    private LabelResponse map(Label label) {

        return LabelResponse.builder()
                .id(label.getId())
                .name(label.getName())
                .build();
    }
}