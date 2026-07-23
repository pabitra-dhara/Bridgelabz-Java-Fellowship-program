package com.fundoo.labelservice.controller;

import com.fundoo.labelservice.dto.request.LabelRequest;
import com.fundoo.labelservice.dto.response.ApiResponse;
import com.fundoo.labelservice.dto.response.LabelResponse;
import com.fundoo.labelservice.service.LabelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PostMapping
    public ApiResponse<LabelResponse> createLabel(
            @Valid @RequestBody LabelRequest request) {

        return new ApiResponse<>(
                "Label Created",
                labelService.createLabel(request));
    }

    @GetMapping
    public ApiResponse<List<LabelResponse>> getLabels() {

        return new ApiResponse<>(
                "Success",
                labelService.getLabels());
    }

    @PutMapping("/{id}")
    public ApiResponse<LabelResponse> updateLabel(
            @PathVariable Long id,
            @RequestBody LabelRequest request) {

        return new ApiResponse<>(
                "Updated",
                labelService.updateLabel(id, request));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteLabel(
            @PathVariable Long id) {

        labelService.deleteLabel(id);

        return new ApiResponse<>(
                "Deleted Successfully",
                null);
    }

    @GetMapping("/{id}")
    public ApiResponse<LabelResponse> getLabel(
            @PathVariable Long id) {

        return new ApiResponse<>(
                "Success",
                labelService.getLabel(id));
    }
}

