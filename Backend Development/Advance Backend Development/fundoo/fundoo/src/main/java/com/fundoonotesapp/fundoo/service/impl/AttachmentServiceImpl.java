package com.fundoonotesapp.fundoo.service.impl;

import com.fundoonotesapp.fundoo.dto.response.AttachmentResponse;
import com.fundoonotesapp.fundoo.entity.Attachment;
import com.fundoonotesapp.fundoo.entity.Note;
import com.fundoonotesapp.fundoo.entity.User;
import com.fundoonotesapp.fundoo.repository.AttachmentRepository;
import com.fundoonotesapp.fundoo.repository.NoteRepository;
import com.fundoonotesapp.fundoo.repository.UserRepository;
import com.fundoonotesapp.fundoo.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    private User getLoggedInUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    @Override
    public AttachmentResponse uploadFile(
            Long noteId,
            MultipartFile file) {

        try {

            User user = getLoggedInUser();

            Note note = noteRepository
                    .findByIdAndUser(noteId, user)
                    .orElseThrow(() ->
                            new RuntimeException("Note not found"));

            String storedName =
                    UUID.randomUUID() + "_"
                            + file.getOriginalFilename();

            Path uploadPath = Paths.get(uploadDir);

            Files.createDirectories(uploadPath);

            Path filePath =
                    uploadPath.resolve(storedName);

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);

            Attachment attachment =
                    Attachment.builder()
                            .originalFileName(
                                    file.getOriginalFilename())
                            .storedFileName(storedName)
                            .fileType(file.getContentType())
                            .fileSize(file.getSize())
                            .filePath(filePath.toString())
                            .note(note)
                            .build();

            attachmentRepository.save(attachment);

            return map(attachment);

        } catch (IOException e) {

            throw new RuntimeException(
                    "File upload failed", e);
        }
    }

    @Override
    public List<AttachmentResponse> getAttachments(
            Long noteId) {

        User user = getLoggedInUser();

        Note note = noteRepository
                .findByIdAndUser(noteId, user)
                .orElseThrow(() ->
                        new RuntimeException("Note not found"));

        return attachmentRepository
                .findByNoteId(note.getId())
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public void deleteAttachment(
            Long attachmentId) {

        try {

            User user = getLoggedInUser();

            Attachment attachment =
                    attachmentRepository
                            .findByIdAndNoteUserId(
                                    attachmentId,
                                    user.getId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Attachment not found"));

            Files.deleteIfExists(
                    Paths.get(
                            attachment.getFilePath()));

            attachmentRepository.delete(attachment);

        } catch (IOException e) {

            throw new RuntimeException(
                    "File deletion failed", e);
        }
    }

    private AttachmentResponse map(
            Attachment attachment) {

        return AttachmentResponse.builder()
                .id(attachment.getId())
                .originalFileName(
                        attachment.getOriginalFileName())
                .fileType(
                        attachment.getFileType())
                .fileSize(
                        attachment.getFileSize())
                .build();
    }
}