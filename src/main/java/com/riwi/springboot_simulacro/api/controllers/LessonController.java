package com.riwi.springboot_simulacro.api.controllers;

import com.riwi.springboot_simulacro.api.dto.request.LessonReq;
import com.riwi.springboot_simulacro.api.dto.request.UserReq;
import com.riwi.springboot_simulacro.api.dto.response.LessonResp;
import com.riwi.springboot_simulacro.api.dto.response.UserResp;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.ILessonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
public class LessonController {

    @Autowired
    private final ILessonService lessonService;


    @PostMapping
    public ResponseEntity<LessonResp> insert(
            @RequestBody LessonReq request
    ){
        return ResponseEntity.ok(this.lessonService.create(request));
    }
}
