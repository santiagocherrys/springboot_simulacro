package com.riwi.springboot_simulacro.api.dto.request;

import jakarta.persistence.Column;
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
public class CourseReq {
    @Size(min = 0, max = 100, message = "El nombre del curso supera la cantidad de caracteres")
    @NotBlank(message = "El nombre del curso es requerido")
    private String course_name;
    private String description;
    @NotNull(message ="Por favor ingrese instructor_id")
    private Integer instructor_id;
}
