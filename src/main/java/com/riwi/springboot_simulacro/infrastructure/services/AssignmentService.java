package com.riwi.springboot_simulacro.infrastructure.services;

import com.riwi.springboot_simulacro.api.dto.request.AssignmentReq;
import com.riwi.springboot_simulacro.api.dto.response.AssignmentResp;
import com.riwi.springboot_simulacro.api.dto.response.LessonToAssignmentResp;
import com.riwi.springboot_simulacro.api.dto.response.SubmissionToAssignmentResp;
import com.riwi.springboot_simulacro.api.dto.response.UserToEnrollmentResponse;
import com.riwi.springboot_simulacro.domain.entities.Assignment;
import com.riwi.springboot_simulacro.domain.entities.Lesson;
import com.riwi.springboot_simulacro.domain.entities.Submission;
import com.riwi.springboot_simulacro.domain.entities.User;
import com.riwi.springboot_simulacro.domain.repositories.AssignmentRepository;
import com.riwi.springboot_simulacro.domain.repositories.LessonRepository;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.IAssignmentService;
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
public class AssignmentService implements IAssignmentService {

    @Autowired
    private final AssignmentRepository assignmentRepository;

    @Autowired
    private final LessonRepository lessonRepository;
    @Override
    public void delete(Integer id) {

        this.assignmentRepository.delete(find(id));
    }

    @Override
    public AssignmentResp create(AssignmentReq request) {
        //se revisa el id de lesson sea valido
        Lesson lesson = this.lessonRepository.findById(request.getLesson_id()).orElseThrow(() -> new IdNotFoundException("Lesson"));

        Assignment assignment = new Assignment();
        assignment.setLesson(lesson);
        assignment.setSubmissions(new ArrayList<>());

        //Se convierte el request a la entidad
        assignment = this.requestToAssignment(request, assignment);
        System.out.println("Esto es assigments " + assignment);
        return this.entityToResponse(this.assignmentRepository.save(assignment));


    }

    @Override
    public AssignmentResp update(Integer id, AssignmentReq request) {
        //Se busca el Assignment

        Assignment assignment = this.find(id);

        assignment = this.requestToAssignment(request, assignment);

        return this.entityToResponse(this.assignmentRepository.save(assignment));
    }

    @Override
    public Page<AssignmentResp> getAll(int page, int size) {
        if(page < 0){
            page = 0;
        }

        //Se crea la paginación
        PageRequest pagination = PageRequest.of(page, size);

        //Se obtiene  todos los assigments  de la base de datos
        return this.assignmentRepository.findAll(pagination).map(assignment -> this.entityToResponse(assignment));
    }

    @Override
    public AssignmentResp getById(Integer id) {

        return this.entityToResponse(this.find(id));
    }

    private Assignment requestToAssignment(AssignmentReq request, Assignment assignment){

        return Assignment.builder()
                .assignment_id(assignment.getAssignment_id())
                .assignment_title(request.getAssignment_title())
                .description(request.getDescription())
                .due_date(request.getDue_date())
                .lesson(assignment.getLesson())
                .submissions(assignment.getSubmissions())
                .build();
    }

    private AssignmentResp entityToResponse(Assignment assignment){
        AssignmentResp response = new AssignmentResp();

        //Se copia alas propiedades de las entidad al dto de respuesta
        BeanUtils.copyProperties(assignment, response);

        //Se copia el id
        response.setId(assignment.getAssignment_id());
        //Se crea la instancia de dto de lesson dentro de assignment
        LessonToAssignmentResp lessonResp = new LessonToAssignmentResp();

        //Se copia la respuesta
        lessonResp.setId(assignment.getLesson().getLesson_id());
        lessonResp.setLesson_title(assignment.getLesson().getLesson_title());
        lessonResp.setContent(assignment.getLesson().getContent());

        response.setSubmissions(assignment.getSubmissions().stream().map(submission -> this.submissionToResponse(submission)).collect(Collectors.toList()));
        response.setLesson(lessonResp);

        return response;

    }
    private Assignment find(Integer id){
        return this.assignmentRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Assignment"));
    }

    private SubmissionToAssignmentResp submissionToResponse(Submission entity){
        SubmissionToAssignmentResp response = new SubmissionToAssignmentResp();

        response.setId(entity.getSubmission_id());
        response.setContent(entity.getContent());
        response.setSubmission_date(entity.getSubmission_date());
        response.setGrade(entity.getGrade());
        response.setUser(this.userToResponse(entity.getUser()));

        return response;
    }

    private UserToEnrollmentResponse userToResponse(User entity){
        UserToEnrollmentResponse response = new UserToEnrollmentResponse();

        response.setId(entity.getUser_id());
        response.setUsername(entity.getUsername());
        response.setEmail(entity.getEmail());
        response.setFull_name(entity.getFull_name());
        response.setRole(entity.getRole());

        return response;
    }

}
