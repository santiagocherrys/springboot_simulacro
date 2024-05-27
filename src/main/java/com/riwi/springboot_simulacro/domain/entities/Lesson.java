package com.riwi.springboot_simulacro.domain.entities;

import jakarta.persistence.*;

import java.util.Map;

@Entity(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lesson_id;
    @Column(length =  100, nullable = false)
    private String lesson_title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;
    @Column(nullable = false)
    private Integer course_id;
}
