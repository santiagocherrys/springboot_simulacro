package com.riwi.springboot_simulacro.api.dto.request;

import com.riwi.springboot_simulacro.util.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
    private String username;
    private String password;
    private String email;
    private String full_name;
    private Role role;
}
