package com.riwi.springboot_simulacro.api.error_handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder //Crea un constructor con el super de Serializable
public class BaseErrorResponse implements Serializable {
    private String status;
    private Integer code;
}
