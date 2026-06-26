package com.fundoo.reminderservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reminders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long noteId;
    private String noteTitle;
    @Column(length = 5000)
    private String noteDescription;
    private LocalDateTime reminderTime;
    private boolean notified;
}