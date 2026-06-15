package com.fundoonotesapp.fundoo.repository;

import com.fundoonotesapp.fundoo.entity.Note;
import com.fundoonotesapp.fundoo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
    List<Note> findByUserAndTrashedFalse(User user);
    List<Note> findByUserAndPinnedTrue(User user);
    List<Note> findByUserAndArchivedTrue(User user);
    List<Note> findByTitleContainingIgnoreCase(String title);
    List<Note> findByUserAndPinnedTrueAndTrashedFalse(User user);
    List<Note> findByUserAndArchivedTrueAndTrashedFalse(User user);
    List<Note> findByUserAndTrashedTrue(User user);
    List<Note> findByUserAndTitleContainingIgnoreCase(User user, String title);
    List<Note> findByUserAndDescriptionContainingIgnoreCase(User user, String description);
    Optional<Note> findByIdAndUser(Long id, User user);
}