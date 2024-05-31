package com.riwi.springboot_simulacro.domain.entities;

import com.riwi.springboot_simulacro.api.dto.response.CourseBasicResp;
import com.riwi.springboot_simulacro.util.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    @Column(length = 100)
    private String full_name;
    @Enumerated(EnumType.STRING)//Esto hace que imprima el string y no un numero
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL , orphanRemoval = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Submission> submissions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy="instructor",
               cascade=CascadeType.ALL,
                orphanRemoval = false,
                fetch = FetchType.EAGER
                )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Course> courses;

    //OJO EL MAPPEDBY si lo llamo userSender al otro lado en Many to One la instancia lo tiene que recibir con el mismo nombre
    //en la otra entidad
    @OneToMany(mappedBy="userSender",
            cascade=CascadeType.ALL,
            orphanRemoval = false,
            fetch = FetchType.EAGER
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Message> messageSenders;

    @OneToMany(mappedBy="userReceiver",
            cascade=CascadeType.ALL,
            orphanRemoval = false,
            fetch = FetchType.EAGER
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Message> messageReceivers;


}
