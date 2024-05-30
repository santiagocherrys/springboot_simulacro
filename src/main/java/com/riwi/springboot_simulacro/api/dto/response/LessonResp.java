package com.riwi.springboot_simulacro.api.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonResp {
    private Integer id;
    private String lesson_title;
    private String content;
    private CourseResp course;
    private List<AssignmentResp> assignments;
}
