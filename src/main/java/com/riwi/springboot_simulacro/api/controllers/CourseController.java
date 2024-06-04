package com.riwi.springboot_simulacro.api.controllers;

import com.riwi.springboot_simulacro.api.dto.request.CourseBasicReq;
import com.riwi.springboot_simulacro.api.dto.request.CourseReq;
import com.riwi.springboot_simulacro.api.dto.response.CourseResp;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.ICourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
@Tag(name = "Courses")
public class CourseController {
    @Autowired
    private final ICourseService courseService;

    @Operation(
            summary = "Lista todos los Courses con paginación",
            description = "Debes enviar la página y el tamaño de la página para recibir todas las variabes correspondientes"
    )
    @GetMapping
    public ResponseEntity<Page<CourseResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(this.courseService.getAll(page -1 , size));
    }

    @Operation(
            summary = "Obtiene un course por id",
            description = "Debes enviar el id para filtrar por id"
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseResp> get(@PathVariable Integer id){
        return ResponseEntity.ok(this.courseService.getById(id));
    }

    @Operation(
            summary = "Crea un course",
            description = "Crea un course"
    )
    @PostMapping
    public ResponseEntity<CourseResp> insert(
            @Validated
            @RequestBody CourseReq request
    ){
        return ResponseEntity.ok(this.courseService.create(request));
    }

    @Operation(
            summary = "Borra un course por id",
            description = "Borra un course por id"
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        //Se borra entidad
        this.courseService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualiza un course por id",
            description = "Actualiza un course por id"
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<CourseResp> update(@PathVariable Integer id,
                                             @Validated
                                             @RequestBody CourseBasicReq request){
        //Este paso se hace ya que por el CRUD solo resive CourseReq pero
        //necesitamos un campo menos que es el CourseBasic por eso
        //se envía vacio
        CourseReq courseReq = new CourseReq();
        courseReq.setCourse_name(request.getCourse_name());
        courseReq.setDescription(request.getDescription());
        courseReq.setInstructor_id(0);
        return ResponseEntity.ok(this.courseService.update(id,courseReq));
    }

}
