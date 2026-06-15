package com.fundoonotesapp.fundoo.controller;

import com.fundoonotesapp.fundoo.dto.response.ApiResponse;
import com.fundoonotesapp.fundoo.dto.response.AttachmentResponse;
import com.fundoonotesapp.fundoo.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/notes/{noteId}")
    public ApiResponse<AttachmentResponse> uploadFile(
            @PathVariable Long noteId,
            @RequestParam("file") MultipartFile file) {

        return new ApiResponse<>(
                "File Uploaded Successfully",
                attachmentService.uploadFile(noteId, file)
        );
    }

    @GetMapping("/notes/{noteId}")
    public ApiResponse<List<AttachmentResponse>> getAttachments(
            @PathVariable Long noteId) {

        return new ApiResponse<>(
                "Attachments Retrieved Successfully",
                attachmentService.getAttachments(noteId)
        );
    }

    @DeleteMapping("/{attachmentId}")
    public ApiResponse<String> deleteAttachment(
            @PathVariable Long attachmentId) {

        attachmentService.deleteAttachment(attachmentId);

        return new ApiResponse<>(
                "Attachment Deleted Successfully",
                "Deleted"
        );
    }
}