package com.riwi.springboot_simulacro.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentBasicReq {
    @Size(min = 0 , max = 100, message = "El nombre de assignment_tittle supera la cantidad de caracteres permitidos")
    @NotBlank(message = "El assignment_title es requerido")
    private String assignment_title;
    private String  description;

}
