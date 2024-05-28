package com.riwi.springboot_simulacro.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Entity(name = "lesson")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lesson_id;
    @Column(length =  100, nullable = false)
    private String lesson_title;
    @Column(columnDefinition = "TEXT")
    private String content;

    //relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Assignment> assignments;
}
