package com.riwi.springboot_simulacro.api.controllers;

import com.riwi.springboot_simulacro.domain.entities.User;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.IUserservicePrueba;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {

    private final IUserservicePrueba objIuserService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return ResponseEntity.ok((this.objIuserService.save(user)));
    }

}
