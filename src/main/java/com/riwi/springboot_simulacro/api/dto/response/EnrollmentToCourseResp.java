package com.riwi.springboot_simulacro.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentToCourseResp {
    private Integer id;
    private LocalDate enrollment_date;
    private UserToEnrollmentResponse estudent;
}
