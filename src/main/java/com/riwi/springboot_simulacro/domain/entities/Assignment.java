package com.riwi.springboot_simulacro.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer assignment_id;
    @Column(length = 100, nullable = false)
    private String assignment_title;
    @Column(columnDefinition = "TEXT")
    private String  description;
    private LocalDate due_date;
    @Column(nullable = false)
    private Integer lesson_id;
}
