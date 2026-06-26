package com.fundoo.reminderservice.repository;

import com.fundoo.reminderservice.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReminderRepository
        extends JpaRepository<Reminder, Long> {

    List<Reminder> findByReminderTimeLessThanEqualAndNotifiedFalse(
            LocalDateTime time);

    List<Reminder> findByUserId(Long userId);
}