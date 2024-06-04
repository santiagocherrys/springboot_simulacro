package com.riwi.springboot_simulacro.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentNoTimeReq {
    @Size(min = 0 , max = 100, message = "El nombre de assignment_tittle supera la cantidad de caracteres permitidos")
    @NotBlank(message = "El assignment_title es requerido")
    private String assignment_title;
    private String  description;
    @NotNull(message = "Se requiere campo de lesson_id")
    @Min(value = 0, message = "Valor tiene que ser igual o mayor a 0")
    private Integer lesson_id;
}
