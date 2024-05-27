package com.riwi.springboot_simulacro.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "submission")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer submission_id;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDate submission_data;
    @Column(precision = 5, scale = 2 , nullable = false)
    private BigDecimal grade;
    private Integer user_id;
    private Integer assignment_id;
}
