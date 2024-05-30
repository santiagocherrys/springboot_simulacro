package com.riwi.springboot_simulacro.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentReq {
    private Integer user_id;
    private Integer course_id;
    private LocalDate enrollment_date;
}
