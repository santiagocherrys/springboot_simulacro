package com.riwi.springboot_simulacro.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageToCourseResp {
    private Integer id;
    private String message_content;
    private LocalDateTime sent_date;
    private UserToEnrollmentResponse sender;
    private UserToEnrollmentResponse receiver;
}
