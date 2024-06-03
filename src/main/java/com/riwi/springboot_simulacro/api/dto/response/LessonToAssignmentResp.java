package com.riwi.springboot_simulacro.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonToAssignmentResp {
    private Integer id;
    private String lesson_title;
    private String content;
}
