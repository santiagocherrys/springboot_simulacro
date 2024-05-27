package com.riwi.springboot_simulacro.domain.entities;

import jakarta.persistence.*;

@Entity(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer course_id;
    @Column(length = 100, nullable = false)
    private String course_name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private Integer instructor_id;

}
