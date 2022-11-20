package com.jonathangoycochea.taskapp.persistence.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime creationDate;
    private LocalDateTime estimatedCompletionDate;
    private boolean completed;
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
}
