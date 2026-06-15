package com.fundoonotesapp.fundoo.repository;

import com.fundoonotesapp.fundoo.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttachmentRepository
        extends JpaRepository<Attachment, Long> {

    List<Attachment> findByNoteId(Long noteId);

    Optional<Attachment> findByIdAndNoteUserId(
            Long attachmentId,
            Long userId);
}