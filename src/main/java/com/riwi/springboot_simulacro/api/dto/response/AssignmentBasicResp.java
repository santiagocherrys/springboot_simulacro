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
public class AssignmentBasicResp {
    private Integer id;
    private String assignment_title;
    private String  description;
    private LocalDate due_date;
    private List<SubmissionResp> submissions;
}


