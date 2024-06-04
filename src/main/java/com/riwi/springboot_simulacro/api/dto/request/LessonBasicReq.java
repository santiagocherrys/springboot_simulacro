package com.riwi.springboot_simulacro.api.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonBasicReq {
    @Size(min = 0, max = 100, message = "El titulo de la lession supera la cantidad de caracteres")
    @NotBlank(message = "El titulo de la lección es requerido")
    private String lesson_title;
    private String content;
}
