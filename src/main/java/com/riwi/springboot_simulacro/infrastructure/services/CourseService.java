package com.riwi.springboot_simulacro.infrastructure.services;

import com.riwi.springboot_simulacro.api.dto.request.CourseReq;
import com.riwi.springboot_simulacro.api.dto.response.*;
import com.riwi.springboot_simulacro.domain.entities.*;
import com.riwi.springboot_simulacro.domain.repositories.CourseRepository;
import com.riwi.springboot_simulacro.domain.repositories.UserRepository;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.ICourseService;
import com.riwi.springboot_simulacro.util.exceptions.IdNotFoundException;
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
        var response = this.find(id);
        return this.entityToResponse(response);
    }

    private Course requestToCourse(CourseReq request, Course course){

        //Se hace copia
        course.setCourse_name(request.getCourse_name());
        course.setDescription(request.getDescription());
        //Se busca instructor
        System.out.println("Este es el id del instructor" + request.getInstructor_id());
        User instructor = this.userRepository.findById(request.getInstructor_id()).orElseThrow(() -> new IdNotFoundException("User"));
        System.out.println("El objeto de java de instructor " + instructor);
        course.setInstructor(instructor);

        return course;
    }

    private CourseResp entityToResponse(Course entity){
        CourseResp response = new CourseResp();


        //Se copia objeto
        BeanUtils.copyProperties(entity,response);

        //Se mapean las lecciones que tiene course
        response.setLessons(entity.getLessons().stream()
                .map(lesson -> this.lessonToResponse(lesson))
                .collect(Collectors.toList()));

        //Se mapean los enrollments(inscripciones que tiene cada courso)

        response.setEnrollments(entity.getEnrollments().stream()
                .map(enrollment -> this.enrollmentToResponse(enrollment))
                .collect(Collectors.toList()));



        response.setMessages(entity.getMessages().stream()
                .map(message -> this.messageToCourseResp(message)).collect(Collectors.toList()));

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

    private EnrollmentToCourseResp enrollmentToResponse(Enrollment entity){
        EnrollmentToCourseResp response = new EnrollmentToCourseResp();

        System.out.println("esto es lo que me llega de Enrollment " + entity);
        //Se copian las propiedades
        //BeanUtils.copyProperties(entity, response);
        response.setId(entity.getEnrollment_id());
        response.setEnrollment_date(entity.getEnrollment_date());
        response.setEstudent(this.userToEnrollment(entity.getUser()));

        return response;
    }

    //Se hace funcion para buscar entidad
    private Course find(Integer id){
        return this.courseRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Course"));
    }

    private UserToEnrollmentResponse userToEnrollment(User user){
        UserToEnrollmentResponse userToEnrollmentResponse = new UserToEnrollmentResponse();

        //Copiamos los valores
        userToEnrollmentResponse.setId(user.getUser_id());
        userToEnrollmentResponse.setUsername(user.getUsername());
        userToEnrollmentResponse.setEmail(user.getEmail());
        userToEnrollmentResponse.setFull_name(user.getFull_name());
        userToEnrollmentResponse.setRole(user.getRole());

        return userToEnrollmentResponse;

    }

    private MessageToCourseResp messageToCourseResp(Message message){
        MessageToCourseResp messageToCourseResp = new MessageToCourseResp();

        //Se copia los campos
        messageToCourseResp.setId(message.getMessage_id());
        messageToCourseResp.setMessage_content(message.getMessage_content());
        messageToCourseResp.setSent_date(message.getSent_date());
        messageToCourseResp.setSender(this.userToEnrollment(message.getUserSender()));
        messageToCourseResp.setReceiver(this.userToEnrollment(message.getUserReceiver()));

        return messageToCourseResp;

    }
}
