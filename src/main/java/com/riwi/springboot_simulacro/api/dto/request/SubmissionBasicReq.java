package com.riwi.springboot_simulacro.api.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
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
public class SubmissionBasicReq {
    private String content;
    @DecimalMax(value = "999.99", message = " Valor tiene que ser menor o igual que 999.99")
    @DecimalMin(value = "0.00" , message = "Valor tiene que ser mayor o igual a 0.00")
    private BigDecimal grade;
}
