package com.riwi.springboot_simulacro.api.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResp {
    private Integer id;
    private String message_content;
    private LocalDateTime sent_date;
    private CourseResp course;
    private UserResp sender;
    private UserResp receiver;
}
