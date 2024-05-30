package com.riwi.springboot_simulacro.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "enrollment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enrollment_id;
    private LocalDate enrollment_date;

    //relacion
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")//esta parte no es necesaria referencedColumnName = "course_id"
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

}
