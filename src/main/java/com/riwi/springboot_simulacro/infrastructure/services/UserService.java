package com.riwi.springboot_simulacro.infrastructure.services;

import com.riwi.springboot_simulacro.api.dto.request.UserReq;
import com.riwi.springboot_simulacro.api.dto.response.*;
import com.riwi.springboot_simulacro.domain.entities.*;
import com.riwi.springboot_simulacro.domain.repositories.UserRepository;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.IUserService;
import com.riwi.springboot_simulacro.util.enums.Role;
import com.riwi.springboot_simulacro.util.exceptions.IdNotFoundException;
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

        System.out.println("Esto es la consulta especializada: " +this.userRepository.findByRole(Role.INSTRUCTOR));
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

        List<EnrollmentToUserResp> enrollment = entity.getEnrollments()
                .stream()
                .map(this::entityToResponseEnrollment)
                .collect(Collectors.toList());

        List<SubmissionToUserResp> submission = entity.getSubmissions()
                .stream()
                .map(this::entityToResponseSubmission)
                .collect(Collectors.toList());

        List<MessageResp> messageReceiver = entity.getMessageReceivers()
                .stream()
                .map(this::entityToResponseMessage)
                .collect(Collectors.toList());

        List<MessageResp> messageSender = entity.getMessageSenders()
                .stream()
                .map(this::entityToResponseMessage)
                .collect(Collectors.toList());

        return UserResp.builder()
                .id(entity.getUser_id())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .email(entity.getEmail())
                .full_name(entity.getFull_name())
                .role(entity.getRole())
                .courses(courses)
                .enrollments(enrollment)
                .messageReceivers(messageReceiver)
                .messageSenders(messageSender)
                .submissions(submission)
                .build();
    }

    private CourseBasicResp entityToResponseCourse(Course entity){



        return CourseBasicResp.builder()
                .id(entity.getCourse_id())
                .course_name(entity.getCourse_name())
                .description(entity.getDescription())
                .build();
    }

    private EnrollmentToUserResp entityToResponseEnrollment(Enrollment entity){
        return EnrollmentToUserResp.builder()
                .id(entity.getEnrollment_id())
                .enrollment_date(entity.getEnrollment_date())
                .course(this.entityToResponseCourse(entity.getCourse()))
                .build();
    }

    private SubmissionToUserResp entityToResponseSubmission(Submission entity){
        return SubmissionToUserResp.builder()
                .id(entity.getSubmission_id())
                .content(entity.getContent())
                .submission_date(entity.getSubmission_date())
                .grade(entity.getGrade())
                .assignment(this.assignmentToBasicResp(entity.getAssignment()))
                .build();
    }

    private MessageResp entityToResponseMessage(Message entity){

        return MessageResp.builder()
                .id(entity.getMessage_id())
                .message_content(entity.getMessage_content())
                .sent_date(entity.getSent_date())
                .course(this.courseToBasicResp(entity.getCourse()))
                .sender(this.userToCourseResp(entity.getUserSender()))
                .receiver(this.userToCourseResp(entity.getUserReceiver()))
                .build();

    }

    private AssignmentBasicResp assignmentToBasicResp(Assignment entity){
        AssignmentBasicResp assignmentBasicResp = new AssignmentBasicResp();

        assignmentBasicResp.setId(entity.getAssignment_id());
        assignmentBasicResp.setAssignment_title(entity.getAssignment_title());
        assignmentBasicResp.setDescription(entity.getDescription());
        assignmentBasicResp.setDue_date(entity.getDue_date());

        return assignmentBasicResp;
    }

    private MessageResp entityToMessage(Message message){

        return MessageResp.builder()
                .id(message.getMessage_id())
                .message_content(message.getMessage_content())
                .sent_date(message.getSent_date())
                .course(this.courseToBasicResp(message.getCourse()))
                .receiver(this.userToCourseResp(message.getUserReceiver()))
                .sender(this.userToCourseResp(message.getUserSender()))
                .build();
    }

    private CourseBasicResp courseToBasicResp(Course course){
        CourseBasicResp courseBasicResp = new CourseBasicResp();

        return CourseBasicResp.builder()
                .id(course.getCourse_id())
                .course_name(course.getCourse_name())
                .description(course.getDescription())
                .build();
    }

    private UserToCourseResp  userToCourseResp(User user){


        return UserToCourseResp.builder()
                .id(user.getUser_id())
                .username((user.getUsername()))
                .password(user.getPassword())
                .email(user.getEmail())
                .full_name(user.getFull_name())
                .role(user.getRole())
                .build();
    }

    private User find(Integer id){
        return this.userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("User"));
    }
}
