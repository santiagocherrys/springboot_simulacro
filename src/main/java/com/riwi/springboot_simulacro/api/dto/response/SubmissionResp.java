package com.riwi.springboot_simulacro.api.dto.response;

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
public class SubmissionResp {
    private Integer id;
    private String content;
    private LocalDate submission_date;
    private BigDecimal grade;
    private UserResp user;
    private AssignmentResp assignment;
}
