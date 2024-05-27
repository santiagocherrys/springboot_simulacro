package com.riwi.springboot_simulacro.domain.entities;

import com.riwi.springboot_simulacro.util.enums.Role;
import jakarta.persistence.*;

@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_id;
    @Column(length = 50, nullable = false)
    private String username;
    @Column(length = 255, nullable = false)
    private String password;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 100, nullable = false)
    private String full_name;
    @Enumerated(EnumType.STRING)//Esto hace que imprima el string y no un numero
    @Column(nullable = false)
    private Role role;

}
