package com.riwi.springboot_simulacro.api.dto.request;

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
public class SubmissionPostReq {
    private String content;
    private Integer user_id;
    private Integer assignment_id;
}
