package com.riwi.springboot_simulacro.api.dto.response;

import jakarta.persistence.Column;
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
public class AssignmentResp {
    private Integer id;
    private String assignment_title;
    private String  description;
    private LocalDate due_date;
    private LessonResp lesson;
    private List<SubmissionResp> submissions;
}
