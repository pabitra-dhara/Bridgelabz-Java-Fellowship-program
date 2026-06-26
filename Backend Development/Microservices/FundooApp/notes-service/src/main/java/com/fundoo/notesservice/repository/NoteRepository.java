package com.fundoo.notesservice.repository;

import com.fundoo.notesservice.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUserId(Long userId);

    List<Note> findByUserIdAndTrashedFalse(Long userId);

    List<Note> findByUserIdAndPinnedTrue(Long userId);

    List<Note> findByUserIdAndArchivedTrue(Long userId);

    List<Note> findByTitleContainingIgnoreCase(String title);

    List<Note> findByUserIdAndPinnedTrueAndTrashedFalse(Long userId);

    List<Note> findByUserIdAndArchivedTrueAndTrashedFalse(Long userId);

    List<Note> findByUserIdAndTrashedTrue(Long userId);

    List<Note> findByUserIdAndTitleContainingIgnoreCase(
            Long userId,
            String title);

    List<Note> findByUserIdAndDescriptionContainingIgnoreCase(
            Long userId,
            String description);
}