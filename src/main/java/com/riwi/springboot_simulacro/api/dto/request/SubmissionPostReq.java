package com.riwi.springboot_simulacro.api.dto.request;

import jakarta.validation.constraints.NotNull;
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
    @NotNull(message ="Por favor ingrese user_id")
    private Integer user_id;
    @NotNull(message ="Por favor ingrese assignment_id")
    private Integer assignment_id;
}
