package com.riwi.springboot_simulacro.api.dto.request;

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
public class MessageReq {
    private Integer sender_id;
    private Integer receiver_id;
    private Integer course_id;
    private String message_content;
    private LocalDateTime sent_date;
}
