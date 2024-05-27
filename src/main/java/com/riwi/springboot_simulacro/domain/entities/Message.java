package com.riwi.springboot_simulacro.domain.entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer message_id;
    private Integer sender_id;
    private Integer receiver_id;
    private Integer course_id;
    private String message_content;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime sent_date;

}
