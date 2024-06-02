package com.riwi.springboot_simulacro.api.dto.response;

import com.riwi.springboot_simulacro.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResp {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String full_name;
    private Role role;
    private List<EnrollmentToUserResp> enrollments;
    private List<CourseBasicResp> courses;
    private List<MessageResp> messageSenders;
    private List<MessageResp> messageReceivers;
    private List<SubmissionToUserResp> submissions;
}
