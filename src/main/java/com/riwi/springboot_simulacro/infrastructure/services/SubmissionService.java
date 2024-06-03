package com.riwi.springboot_simulacro.infrastructure.services;

import com.riwi.springboot_simulacro.api.dto.request.AssignmentBasicReq;
import com.riwi.springboot_simulacro.api.dto.request.SubmissionReq;
import com.riwi.springboot_simulacro.api.dto.response.*;
import com.riwi.springboot_simulacro.domain.entities.Assignment;
import com.riwi.springboot_simulacro.domain.entities.Lesson;
import com.riwi.springboot_simulacro.domain.entities.Submission;
import com.riwi.springboot_simulacro.domain.entities.User;
import com.riwi.springboot_simulacro.domain.repositories.AssignmentRepository;
import com.riwi.springboot_simulacro.domain.repositories.SubmissionRepository;
import com.riwi.springboot_simulacro.domain.repositories.UserRepository;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.ISubmissionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubmissionService implements ISubmissionService {

    @Autowired
    private final SubmissionRepository submissionRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AssignmentRepository assignmentRepository;
    @Override
    public void delete(Integer id) {
        var submissions = this.find(id);
        this.submissionRepository.delete(submissions);
    }

    @Override
    public SubmissionResp create(SubmissionReq request) {

        //Se revisa que primero existan user y assginment
        User user = this.userRepository.findById(request.getUser_id())
                .orElseThrow();

        Assignment assignment = this.assignmentRepository.findById(request.getAssignment_id()).orElseThrow();
        //Se crea entidad

        Submission submission = new Submission();
        //Se guardan los objetos en submission

        submission.setUser(user);
        submission.setAssignment(assignment);

        //Se convierte el request a la entidad
        submission = this.requestToSubmission(request, submission);

        return this.entityToResponse(this.submissionRepository.save(submission));
    }

    @Override
    public SubmissionResp update(Integer id, SubmissionReq request) {
        //Se busca el submission
        Submission submission = this.find(id);

        submission.setGrade(request.getGrade());
        submission.setContent(request.getContent());

        return this.entityToResponse(this.submissionRepository.save(submission));
    }

    @Override
    public Page<SubmissionResp> getAll(int page, int size) {

        if(page < 0){
            page = 0;
        }

        //Se crea la paginacion
        PageRequest pagination = PageRequest.of(page , size);

        //Se obtiene todas las lessiones de la base de datos
        return this.submissionRepository.findAll(pagination).map(submission -> this.entityToResponse(submission));
    }

    @Override
    public SubmissionResp getById(Integer id) {

        return this.entityToResponse(this.find(id));
    }

    private Submission requestToSubmission(SubmissionReq request, Submission submission){
        submission.setContent(request.getContent());
        submission.setSubmission_date(request.getSubmission_date());
        submission.setGrade(request.getGrade());
        submission.setUser(this.userRepository.findById(request.getUser_id()).orElseThrow());
        submission.setAssignment(this.assignmentRepository.findById(request.getAssignment_id()).orElseThrow());

        return submission;
    }

    private SubmissionResp entityToResponse(Submission submission){
        //Se crea instancia de la respuesta
        SubmissionResp response = new SubmissionResp();

        //Se copia las propiedade de la entidad al dto de respuesta
        BeanUtils.copyProperties(submission, response);
        response.setId(submission.getSubmission_id());
        System.out.println("esto es submission " + submission);


        //Crear la instancia de dto de usuario dentro de submission
        UserToEnrollmentResponse userToEnrollmentResponse = new UserToEnrollmentResponse();

        userToEnrollmentResponse.setId(submission.getUser().getUser_id());
        userToEnrollmentResponse.setUsername(submission.getUser().getUsername());
        userToEnrollmentResponse.setFull_name(submission.getUser().getFull_name());
        userToEnrollmentResponse.setEmail(submission.getUser().getEmail());
        userToEnrollmentResponse.setRole(submission.getUser().getRole());

        //Se añade a el response
        response.setUser(userToEnrollmentResponse);
        System.out.println("Esto es response" + response);
        //Crear la instancia de dto de assignment
        AssignmentToSubmissionResp assignmentToSubmissionResp = new AssignmentToSubmissionResp();

        assignmentToSubmissionResp.setId(submission.getAssignment().getAssignment_id());
        assignmentToSubmissionResp.setAssignment_title(submission.getAssignment().getAssignment_title());
        assignmentToSubmissionResp.setDescription(submission.getAssignment().getDescription());
        assignmentToSubmissionResp.setDue_date(submission.getAssignment().getDue_date());
        assignmentToSubmissionResp.setLesson(lessonToLessonAssigmentResponse(submission.getAssignment().getLesson()));

        response.setAssignment(assignmentToSubmissionResp);

       return response;
    }

    private Submission find(Integer id){
        return this.submissionRepository.findById(id).orElseThrow();
    }

    private LessonToAssignmentResp lessonToLessonAssigmentResponse(Lesson lesson){
        LessonToAssignmentResp lessonToAssignmentResp = new LessonToAssignmentResp();

        lessonToAssignmentResp.setId(lesson.getLesson_id());
        lessonToAssignmentResp.setLesson_title(lesson.getLesson_title());
        lessonToAssignmentResp.setContent(lesson.getContent());
        return lessonToAssignmentResp;
    }
}
