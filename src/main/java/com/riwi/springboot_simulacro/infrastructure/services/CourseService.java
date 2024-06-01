package com.riwi.springboot_simulacro.infrastructure.services;

import com.riwi.springboot_simulacro.api.dto.request.CourseReq;
import com.riwi.springboot_simulacro.api.dto.response.CourseResp;
import com.riwi.springboot_simulacro.api.dto.response.CourseToLessonResp;
import com.riwi.springboot_simulacro.api.dto.response.LessonToCourseResp;
import com.riwi.springboot_simulacro.api.dto.response.UserToCourseResp;
import com.riwi.springboot_simulacro.domain.entities.Course;
import com.riwi.springboot_simulacro.domain.entities.Lesson;
import com.riwi.springboot_simulacro.domain.entities.User;
import com.riwi.springboot_simulacro.domain.repositories.CourseRepository;
import com.riwi.springboot_simulacro.domain.repositories.UserRepository;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.ICourseService;
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
public class CourseService implements ICourseService {

    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final UserRepository userRepository;
    @Override
    public void delete(Integer id) {
        //Se busca la entidad por id y se borra con repository
        this.courseRepository.delete(this.find(id));
    }

    @Override
    public CourseResp create(CourseReq request) {
        Course course = this.requestToCourse(request , new Course());
        System.out.println("Esto es el instructor desde postman " + course.getInstructor());
        return this.entityToResponse(this.courseRepository.save(course));
    }

    @Override
    public CourseResp update(Integer id, CourseReq request) {

        Course course = this.find(id);
        //Se llenan los campos
        course.setCourse_name(request.getCourse_name());
        course.setDescription(request.getDescription());

        System.out.println("Este es el curso " +  course );

        return this.entityToResponse(this.courseRepository.save(course));
    }

    @Override
    public Page<CourseResp> getAll(int page, int size) {
        if(page < 0){
            page = 0;
        }

        PageRequest pagination = PageRequest.of(page,size);

        //expresión lambda inferencial
        return this.courseRepository.findAll(pagination)
                .map(this::entityToResponse);
    }

    @Override
    public CourseResp getById(Integer id) {
        return null;
    }

    private Course requestToCourse(CourseReq request, Course course){

        //Se hace copia
        course.setCourse_name(request.getCourse_name());
        course.setDescription(request.getDescription());
        //Se busca instructor
        System.out.println("Este es el id del instructor" + request.getInstructor_id());
        User instructor = this.userRepository.findById(request.getInstructor_id()).orElseThrow();
        System.out.println("El objeto de java de instructor " + instructor);
        course.setInstructor(instructor);

        return course;
    }

    private CourseResp entityToResponse(Course entity){
        CourseResp response = new CourseResp();


        //Se copia objeto
        BeanUtils.copyProperties(entity,response);

        //Se mapean las lecciones que tiene course
        /*response.setLessons(entity.getLessons().stream()
                .map(lesson -> this.lessonToResponse(lesson))
                .collect(Collectors.toList()));*/
        response.setLessons(new ArrayList<>());
        response.setMessages(new ArrayList<>());
        response.setEnrollments(new ArrayList<>());
        response.setId(entity.getCourse_id());

        //Se copia
        UserToCourseResp instructor = new UserToCourseResp();

        //Se copia objeto
        BeanUtils.copyProperties(entity.getInstructor(),instructor);
        //Se añade el id
        instructor.setId(entity.getInstructor().getUser_id());
        response.setInstructor(instructor);

        //Se busca instructor
        //User instructor = this.userRepository.findById(entity.geti).orElseThrow();

        return response;
    }

    private LessonToCourseResp lessonToResponse(Lesson entity){
        LessonToCourseResp response = new LessonToCourseResp();

        //Se copian las propiedades
        BeanUtils.copyProperties(entity, response);

        return response;
    }

    //Se hace funcion para buscar entidad
    private Course find(Integer id){
        return this.courseRepository.findById(id).orElseThrow();
    }
}
