package com.riwi.springboot_simulacro.api.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentReq {
    private String assignment_title;
    private String  description;
    private LocalDate due_date;
    private Integer lesson_id;
}
