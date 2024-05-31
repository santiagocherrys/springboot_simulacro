package com.riwi.springboot_simulacro.api.dto.response;

import com.riwi.springboot_simulacro.util.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorResp {
    private Integer id;
    private String email;
    private String full_name;
    private Role role;
}
