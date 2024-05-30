package com.riwi.springboot_simulacro.api.controllers;

import com.riwi.springboot_simulacro.api.dto.request.UserReq;
import com.riwi.springboot_simulacro.api.dto.response.UserResp;
import com.riwi.springboot_simulacro.domain.entities.User;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.IUserService;
import com.riwi.springboot_simulacro.infrastructure.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {

    @Autowired
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResp>> getAll(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(this.userService.getAll(page -1,size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResp> getById(@PathVariable Integer id){
        return ResponseEntity.ok(this.userService.getById(id));
    }
    @PostMapping
    public ResponseEntity<UserResp> insert(
            @RequestBody UserReq request
    ){
        return ResponseEntity.ok(this.userService.create(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResp> update(@PathVariable Integer id,
                                           @RequestBody UserReq request){
       return ResponseEntity.ok(this.userService.update(id, request));
    }
}
