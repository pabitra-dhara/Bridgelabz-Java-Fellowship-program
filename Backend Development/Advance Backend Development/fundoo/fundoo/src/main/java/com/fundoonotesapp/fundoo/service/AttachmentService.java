package com.fundoonotesapp.fundoo.service;

import com.fundoonotesapp.fundoo.dto.response.AttachmentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {

    AttachmentResponse uploadFile(
            Long noteId,
            MultipartFile file);

    List<AttachmentResponse> getAttachments(
            Long noteId);

    void deleteAttachment(
            Long attachmentId);
}