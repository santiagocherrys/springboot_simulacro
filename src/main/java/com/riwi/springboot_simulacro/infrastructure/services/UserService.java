package com.riwi.springboot_simulacro.infrastructure.services;

import com.riwi.springboot_simulacro.api.dto.request.UserReq;
import com.riwi.springboot_simulacro.api.dto.response.*;
import com.riwi.springboot_simulacro.domain.entities.Course;
import com.riwi.springboot_simulacro.domain.entities.User;
import com.riwi.springboot_simulacro.domain.repositories.UserRepository;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;
    @Override
    public void delete(Integer id) {
        //se utiliza find para ya despues llamar al repositorio
        this.userRepository.delete(this.find(id));
    }

    @Override
    public UserResp create(UserReq request) {
        User user = this.requestToEntity(request);
        user.setCourses(new ArrayList<>());
        user.setEnrollments(new ArrayList<>());
        user.setMessageReceivers(new ArrayList<>());
        user.setMessageSenders(new ArrayList<>());
        user.setSubmissions(new ArrayList<>());

        return this.entityToResp(this.userRepository.save(user));
    }

    @Override
    public UserResp update(Integer id, UserReq request) {
        User user = this.find(id);

        //Se llena los campos
        System.out.println("Este es el campo ID DIDIDIDIDIDDIDIDIDIDIDI ");
        //user.setUser_id(id);
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setFull_name(request.getFull_name());
        user.setRole(request.getRole());

        return this.entityToResp(this.userRepository.save(user));
    }

    @Override
    public Page<UserResp> getAll(int page, int size) {
        if(page < 0){
            page = 0;
        }

        PageRequest pagination = null;
        pagination = PageRequest.of(page, size);

        return this.userRepository.findAll(pagination)
                .map(this::entityToResp);

    }

    @Override
    public UserResp getById(Integer id) {
        return this.entityToResp(this.find(id));
    }

    private User requestToEntity(UserReq user){
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .full_name(user.getFull_name())
                .role(user.getRole())
                .build();
    }

    private UserResp entityToResp  (User entity){

        List<CourseBasicResp> courses = entity.getCourses()
                .stream()
                .map(this::entityToResponseCourse)
                .collect(Collectors.toList());

        return UserResp.builder()
                .id(entity.getUser_id())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .full_name(entity.getFull_name())
                .role(entity.getRole())
                .courses(courses)
                .enrollments(new ArrayList<>())
                .messageReceivers(new ArrayList<>())
                .messageSenders(new ArrayList<>())
                .submissions(new ArrayList<>())
                .build();
    }

    private CourseBasicResp entityToResponseCourse(Course entity){

        List<LessonResp> lessons = new ArrayList<>();
        List<MessageResp> messages = new ArrayList<>();
        List<EnrollmentResp> enrollments = new ArrayList<>();

        return CourseBasicResp.builder()
                .id(entity.getCourse_id())
                .course_name(entity.getCourse_name())
                .description(entity.getDescription())
                .lessons(lessons)
                .messages(messages)
                .enrollments(enrollments)
                .build();
    }

    private User find(Integer id){
        return this.userRepository.findById(id).orElseThrow();
    }
}
