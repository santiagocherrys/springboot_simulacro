package com.riwi.springboot_simulacro.infrastructure.services;

import com.riwi.springboot_simulacro.api.dto.request.LessonReq;
import com.riwi.springboot_simulacro.api.dto.response.*;
import com.riwi.springboot_simulacro.domain.entities.Assignment;
import com.riwi.springboot_simulacro.domain.entities.Course;
import com.riwi.springboot_simulacro.domain.entities.Lesson;
import com.riwi.springboot_simulacro.domain.entities.User;
import com.riwi.springboot_simulacro.domain.repositories.CourseRepository;
import com.riwi.springboot_simulacro.domain.repositories.LessonRepository;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.ILessonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {

    @Autowired
    private final LessonRepository lessonRepository;

    @Autowired
    private final CourseRepository courseRepository;

    @Override
    public void delete(Integer id) {
        //Se busca la lession a borrar
        var lesson = this.find(id);
        this.lessonRepository.delete(lesson);
    }

    @Override
    public LessonResp create(LessonReq request) {

        //se revisa que el id de course sea valido
        Course course = this.courseRepository.findById(request.getCourse_id())
                .orElseThrow();

        Lesson lessonHelp = new Lesson();
        lessonHelp.setCourse(course);

        //Se convierte el request a la entidad
        Lesson lesson = this.requestToLesson(request, lessonHelp);
        lesson.setAssignments(new ArrayList<>());

        return this.entityToResponse(this.lessonRepository.save(lesson));

    }

    @Override
    public LessonResp update(Integer id, LessonReq request) {
        //Se busca la lession
        Lesson lesson = this.find(id);
        System.out.println("Este es lesson Assignments " + lesson.getAssignments());
        lesson = this.requestToLesson(request, lesson);

        //Este es lesson despues de la copia
        System.out.println("Este es lesson despues de la copia " + lesson);
        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public Page<LessonResp> getAll(int page, int size) {

        if (page < 0) {
            page = 0;
        }

        //Se crea la paginacion
        PageRequest pagination = PageRequest.of(page, size);

        //Se obtiene todas las lessiones de la base de datos
        return this.lessonRepository.findAll(pagination).map(lesson -> this.entityToResponse(lesson));
    }

    @Override
    public LessonResp getById(Integer id) {

        return this.entityToResponse(this.find(id));
    }

    private Lesson requestToLesson(LessonReq request, Lesson entity) {

        return Lesson.builder()
                .lesson_id(entity.getLesson_id())
                .lesson_title(request.getLesson_title())
                .content(request.getContent())
                .course(entity.getCourse())
                .assignments(entity.getAssignments())
                .build();
    }

    private LessonResp entityToResponse(Lesson lesson) {
        //Se crea instalancia de la respuesta
        LessonResp response = new LessonResp();

        //Se copia las propidades de la entidad al dto de respuesta
        BeanUtils.copyProperties(lesson, response);

        //crear la instancia de dto de course dentro de lesson
        CourseToLessonResp courseResp = new CourseToLessonResp();

        System.out.println("El id del curso es " + lesson.getCourse().getCourse_id());
        System.out.println("El id del instructor es " + lesson.getCourse().getInstructor());

        //Se copia las propiedades de la entidad en el dto de respuesta
        //BeanUtils.copyProperties(lesson.getCourse(),courseResp);
        courseResp.setId(lesson.getCourse().getCourse_id());
        courseResp.setCourse_name(lesson.getCourse().getCourse_name());
        courseResp.setDescription(lesson.getCourse().getDescription());

        //Se crea el instructor y el usuario
        InstructorResp instructor = new InstructorResp();
        User usuario = lesson.getCourse().getInstructor();

        //Se llena el instructor
        instructor.setId(usuario.getUser_id());
        instructor.setEmail(usuario.getEmail());
        instructor.setFull_name(usuario.getFull_name());
        instructor.setRole(usuario.getRole());

        courseResp.setInstructor(instructor);

        //Se añade el dto de respuesta de al compañia en la respuesta general
        response.setCourse(courseResp);
        //Se añade el id a la respuesta
        response.setId(lesson.getLesson_id());


        //Se añade assigments
        response.setAssignments(lesson.getAssignments().stream().map(assignment -> this.assigmentToResponse(assignment)).collect(Collectors.toList()));
        return response;
    }

    private Lesson find(Integer id) {
        return this.lessonRepository.findById(id).orElseThrow();
    }

    private AssignmentBasicResp assigmentToResponse(Assignment assignment) {
        AssignmentBasicResp response = new AssignmentBasicResp();
        BeanUtils.copyProperties(assignment, response);
        response.setId(assignment.getAssignment_id());
        return response;
    }
}
