package com.riwi.springboot_simulacro.api.dto.request;

import com.riwi.springboot_simulacro.util.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserReq {
    @Size(min = 0, max = 50, message = "El username supera la cantidad de caracteres permitidos")
    @NotBlank(message = "El username es requerido")
    private String username;
    @NotBlank(message = "El password es requerido")
    private String password;
    @Size(min = 0, max = 100, message = "El email supera la cantidad de caracteres permitidos")
    @NotBlank(message = "El email es requerido")
    private String email;
    @Size(min = 0, max = 100, message = "El full_name supera la cantidad de caracteres permitidos")
    private String full_name;
    private Role role;
}
