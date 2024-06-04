package com.riwi.springboot_simulacro.api.controllers;

import com.riwi.springboot_simulacro.api.dto.request.AssignmentBasicReq;
import com.riwi.springboot_simulacro.api.dto.request.AssignmentNoTimeReq;
import com.riwi.springboot_simulacro.api.dto.request.AssignmentReq;
import com.riwi.springboot_simulacro.api.dto.response.AssignmentResp;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.IAssignmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "assignments")
@AllArgsConstructor
@Tag(name = "Assignments")
public class AssignmentController {
    @Autowired
    private final IAssignmentService assignmentService;

    @Operation(
            summary = "Crea un assignment",
            description = "Crea un assignment"
    )
    @PostMapping
    private ResponseEntity<AssignmentResp> insert(
            @Validated
            @RequestBody AssignmentNoTimeReq response){
        AssignmentReq assignmentReq = new AssignmentReq();

        //Se obtiene fecha
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDAte = dateFormat.format(currentDate);
        //Se conviertea LocalDate
        LocalDate localDate = LocalDate.parse(formattedDAte);

        //Se copia para que tenga tiempo automatico y no del usuario
        assignmentReq.setAssignment_title(response.getAssignment_title());
        assignmentReq.setDescription(response.getAssignment_title());
        assignmentReq.setDue_date(localDate);
        assignmentReq.setLesson_id(response.getLesson_id());

        return ResponseEntity.ok(this.assignmentService.create(assignmentReq));
        //System.out.println("esto es la respuesta " + assignmentReq);
        //return null;

    }

    @Operation(
            summary = "Borra un Assignment por ID",
            description = "Borra un Assignment por ID"
    )
    @DeleteMapping(path = "{id}")
    private ResponseEntity<Map<String,String>> delete(@PathVariable Integer id){
        //Se crea el map
        Map<String,String> response = new HashMap<>();

        //Se agrega mensaje
        response.put("message", "Se eliminó Lession correctamente");

        this.assignmentService.delete(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Lista todas los Assignmnet con paginación",
            description = "Debes enviar la página y el tamaño de la página para recibir todas las variabes correspondientes"
    )
    @GetMapping
    public ResponseEntity<Page<AssignmentResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue= "10" ) int size
    ){
        return ResponseEntity.ok(this.assignmentService.getAll(page - 1, size));
    }

    @Operation(
            summary = "Lista un assignment por id",
            description = "Debes enviar el id para filtrar por id"
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<AssignmentResp> get(
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(this.assignmentService.getById(id));
    }

    //Consulta especial
    @GetMapping(path = "/{lesson_id}/lessons")
    public List<AssignmentResp> getAllByLesson(@PathVariable Integer lesson_id){
        return this.assignmentService.getAllAssignmentsByLesson(lesson_id);
    }

    @Operation(
            summary = "Actualiza una vacante por id",
            description = "Debes enviar el id para actualizar por id"
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<AssignmentResp> update(
            @PathVariable Integer id,
            @Validated
            @RequestBody AssignmentBasicReq request){

        //Se obtiene fecha
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDAte = dateFormat.format(currentDate);
        //Se conviertea LocalDate
        LocalDate localDate = LocalDate.parse(formattedDAte);

        //Se transforma a AssignmentReq
        AssignmentReq assignmentReq = new AssignmentReq();

        assignmentReq.setAssignment_title(request.getAssignment_title());
        assignmentReq.setDescription(request.getDescription());
        assignmentReq.setDue_date(localDate);

        return ResponseEntity.ok(this.assignmentService.update(id,assignmentReq));

    }

}
