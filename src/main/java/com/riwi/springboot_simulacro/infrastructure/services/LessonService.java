package com.riwi.springboot_simulacro.infrastructure.services;

import com.riwi.springboot_simulacro.api.dto.request.LessonReq;
import com.riwi.springboot_simulacro.api.dto.response.CourseToLessonResp;
import com.riwi.springboot_simulacro.api.dto.response.InstructorResp;
import com.riwi.springboot_simulacro.api.dto.response.LessonResp;
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
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {

    @Autowired
    private final LessonRepository lessonRepository;

    @Autowired
    private final CourseRepository courseRepository;
    @Override
    public void delete(Integer id) {
        //this.lessonRepository.delete
    }

    @Override
    public LessonResp create(LessonReq request) {

        //se revisa que el id de course sea valido
        Course course = this.courseRepository.findById(request.getCourse_id())
                .orElseThrow();

        Lesson lessonHelp =  new Lesson();
        lessonHelp.setCourse(course);

        //Se convierte el request a la entidad
        Lesson lesson = this.requestToLesson(request, lessonHelp);


        return this.entityToResponse(this.lessonRepository.save(lesson));

    }

    @Override
    public LessonResp update(Integer id, LessonReq request) {
        return null;
    }

    @Override
    public Page<LessonResp> getAll(int page, int size) {
        return null;
    }

    @Override
    public LessonResp getById(Integer id) {
        return null;
    }

    private Lesson requestToLesson(LessonReq request, Lesson entity ){

        return Lesson.builder()
                .lesson_title(request.getLesson_title())
                .content(request.getContent())
                .course(entity.getCourse())
                .build();
    }

    private LessonResp entityToResponse(Lesson lesson){
        //Se crea instalancia de la respuesta
        LessonResp response = new LessonResp();

        //Se copia las propidades de la entidad al dto de respuesta
        BeanUtils.copyProperties(lesson,response);

        //crear la instancia de dto de course dentro de vacante
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
        return response;
    }
}
