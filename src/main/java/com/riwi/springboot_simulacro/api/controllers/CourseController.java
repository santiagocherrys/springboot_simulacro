package com.riwi.springboot_simulacro.api.controllers;

import com.riwi.springboot_simulacro.api.dto.request.CourseBasicReq;
import com.riwi.springboot_simulacro.api.dto.request.CourseReq;
import com.riwi.springboot_simulacro.api.dto.response.CourseResp;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.ICourseService;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/courses")
@AllArgsConstructor
public class CourseController {
    @Autowired
    private final ICourseService courseService;

    @PostMapping
    public ResponseEntity<CourseResp> insert(
            @RequestBody CourseReq request
    ){
        return ResponseEntity.ok(this.courseService.create(request));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        //Se borra entidad
        this.courseService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CourseResp> update(@PathVariable Integer id,
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
