package com.riwi.springboot_simulacro.api.error_handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsResponse extends BaseErrorResponse {
    private List<String> errors;
}
