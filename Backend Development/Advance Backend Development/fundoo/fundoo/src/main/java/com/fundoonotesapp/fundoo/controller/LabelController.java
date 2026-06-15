package com.fundoonotesapp.fundoo.controller;

import com.fundoonotesapp.fundoo.dto.request.LabelRequest;
import com.fundoonotesapp.fundoo.dto.response.ApiResponse;
import com.fundoonotesapp.fundoo.dto.response.LabelResponse;
import com.fundoonotesapp.fundoo.service.LabelService;
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
                "Deleted",
                null);
    }

    @PutMapping("/{labelId}/notes/{noteId}")
    public ApiResponse<String> addLabelToNote(
            @PathVariable Long labelId,
            @PathVariable Long noteId) {

        labelService.addLabelToNote(
                noteId,
                labelId);

        return new ApiResponse<>(
                "Label Added",
                null);
    }

    @DeleteMapping("/{labelId}/notes/{noteId}")
    public ApiResponse<String> removeLabel(
            @PathVariable Long labelId,
            @PathVariable Long noteId) {

        labelService.removeLabelFromNote(
                noteId,
                labelId);

        return new ApiResponse<>(
                "Label Removed",
                null);
    }
}