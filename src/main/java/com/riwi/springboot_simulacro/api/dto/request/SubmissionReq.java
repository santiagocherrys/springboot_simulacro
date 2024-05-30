package com.riwi.springboot_simulacro.api.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubmissionReq {
    private String content;
    private LocalDate submission_date;
    //esta es para el roll de estudiante
    //private BigDecimal grade;
    private Integer user_id;
    private Integer assignment_id;
}
