package com.riwi.springboot_simulacro.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CourseBasicReq {
    @Size(min = 0, max = 100, message = "El nombre del curso supera la cantidad de caracteres")
    @NotBlank(message = "El nombre del curso es requerido")
    private String course_name;
    private String description;
}
