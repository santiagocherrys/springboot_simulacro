package com.riwi.springboot_simulacro.api.controllers;

import com.riwi.springboot_simulacro.api.dto.request.SubmissionBasicReq;
import com.riwi.springboot_simulacro.api.dto.request.SubmissionPostReq;
import com.riwi.springboot_simulacro.api.dto.request.SubmissionReq;
import com.riwi.springboot_simulacro.api.dto.response.SubmissionResp;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.ISubmissionService;
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
@RequestMapping(path = "/submissions")
@AllArgsConstructor
@Tag(name = "Submissions")
public class SubmissionController {

    @Autowired
    private final ISubmissionService submissionService;

    @Operation(
            summary = "Lista todos los Submissions con paginación",
            description = "Debes enviar la página y el tamaño de la página para recibir todas las variabes correspondientes"
    )
    @GetMapping
    public ResponseEntity<Page<SubmissionResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(this.submissionService.getAll(page - 1,size));
    }

    @Operation(
            summary = "Lista un submission por id",
            description = "Debes enviar el id para filtrar por id"
    )
    @GetMapping(path = "{id}")
    public ResponseEntity<SubmissionResp> get(
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(this.submissionService.getById(id));
    }

    //@GetMapping(path = "/{assignmnet_id}/assignments")
    @GetMapping(path = "/{assignment_id}/assignments")
    public List<SubmissionResp> getAllByAssignment(@PathVariable Integer assignment_id){
        return this.submissionService.findSubmissionByAssignments(assignment_id);

        //return "esta es la variable " + ident;
    }

    @Operation(
            summary = "Elimina un submission por id",
            description = "Debes enviar el id para eliminar"
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id){
        //Crear el map
        Map<String, String> response = new HashMap<>();

        //Se agrega mensaje
        response.put("message", "Se eliminó submission correctamente");

        this.submissionService.delete(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Actualiza una submission por id",
            description = "Debes enviar el id para actualizar"
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<SubmissionResp> update(@PathVariable Integer id,
                                                 @Validated
                                                 @RequestBody SubmissionBasicReq request){
        SubmissionReq submission = new SubmissionReq();
        submission.setContent(request.getContent());
        submission.setGrade(request.getGrade());

        return ResponseEntity.ok(this.submissionService.update(id,submission));
    }

    @Operation(
            summary = "Crea un submission",
            description = "Crea un submission"
    )
    @PostMapping
    public ResponseEntity<SubmissionResp> insert(
            @Validated
            @RequestBody SubmissionPostReq request
    ){
        SubmissionReq submissionReq = new SubmissionReq();
        submissionReq.setContent(request.getContent());
        submissionReq.setUser_id(request.getUser_id());
        submissionReq.setAssignment_id(request.getAssignment_id());

        //Se obtiene fecha
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDAte = dateFormat.format(currentDate);
        //Se conviertea LocalDate
        LocalDate localDate = LocalDate.parse(formattedDAte);

        submissionReq.setSubmission_date(localDate);
        return ResponseEntity.ok(this.submissionService.create(submissionReq));
    }

}
