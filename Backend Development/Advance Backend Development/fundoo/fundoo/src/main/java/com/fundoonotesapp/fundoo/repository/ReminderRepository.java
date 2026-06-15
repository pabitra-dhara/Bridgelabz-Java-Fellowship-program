package com.fundoonotesapp.fundoo.repository;

import com.fundoonotesapp.fundoo.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReminderRepository
        extends JpaRepository<Reminder, Long> {

    List<Reminder> findByReminderTimeLessThanEqualAndNotifiedFalse(
            LocalDateTime time);

    Optional<Reminder> findByIdAndNoteUserId(
            Long reminderId,
            Long userId);

    List<Reminder> findByNoteUserId(
            Long userId);

}