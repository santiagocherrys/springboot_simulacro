package com.riwi.springboot_simulacro.domain.repositories;

import com.riwi.springboot_simulacro.api.dto.request.SubmissionReq;
import com.riwi.springboot_simulacro.domain.entities.Assignment;
import com.riwi.springboot_simulacro.domain.entities.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Integer> {

    List<Submission> findByAssignment(Assignment assignment);
}
