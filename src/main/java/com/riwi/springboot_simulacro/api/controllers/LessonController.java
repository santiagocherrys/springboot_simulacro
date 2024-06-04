package com.riwi.springboot_simulacro.api.controllers;

import com.riwi.springboot_simulacro.api.dto.request.LessonBasicReq;
import com.riwi.springboot_simulacro.api.dto.request.LessonReq;
import com.riwi.springboot_simulacro.api.dto.request.UserReq;
import com.riwi.springboot_simulacro.api.dto.response.LessonResp;
import com.riwi.springboot_simulacro.api.dto.response.UserResp;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.ILessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
@Tag(name = "Lessons")
public class LessonController {

    @Autowired
    private final ILessonService lessonService;
    @Operation(
            summary = "Lista todos las Lessons con paginación",
            description = "Debes enviar la página y el tamaño de la página para recibir todas las variabes correspondientes"
    )
    @GetMapping
    public ResponseEntity<Page<LessonResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(this.lessonService.getAll(page -1, size));
    }

    @Operation(
            summary = "Obtiene una lesson por id",
            description = "Obtiene una lesson por id"
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonResp> get(
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(this.lessonService.getById(id));
    }

    @Operation(
            summary = "Elimina una lesson por id",
            description = "Elimina una lesson por id"
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id){
        //Crear el map
        Map<String, String> response =  new HashMap<>();

        //Se agrega mensaje
        response.put("message", "Se eliminó Lession correctamente");

        this.lessonService.delete(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Actualiza una lesson por id",
            description = "Actualiza una lesson por id"
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<LessonResp> update(@PathVariable Integer id,
                                             @Validated
                                             @RequestBody LessonBasicReq request){
        LessonReq lesson = new LessonReq();
        lesson.setLesson_title(request.getLesson_title());
        lesson.setContent(request.getContent());

        return ResponseEntity.ok(this.lessonService.update(id,lesson));
    }
    @Operation(
            summary = "Crea una lesson",
            description = "Crea una lesson"
    )
    @PostMapping
    public ResponseEntity<LessonResp> insert(
            @Validated
            @RequestBody LessonReq request
    ){
        return ResponseEntity.ok(this.lessonService.create(request));
    }
}
