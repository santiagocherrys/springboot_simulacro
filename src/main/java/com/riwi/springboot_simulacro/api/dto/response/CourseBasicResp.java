package com.riwi.springboot_simulacro.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseBasicResp {
    private Integer id;
    private String course_name;
    private String description;
    private List<LessonResp> lessons;
    private List<MessageResp> messages;
    private List<EnrollmentResp> enrollments;
}
