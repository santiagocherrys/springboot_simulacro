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
public class CourseResp {

    private Integer id;
    private String course_name;
    private String description;
    private UserToCourseResp instructor;
    private List<LessonToCourseResp> lessons;
    private List<MessageResp> messages;
    private List<EnrollmentResp> enrollments;
}
