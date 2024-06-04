package com.riwi.springboot_simulacro.infrastructure.abstract_services;

import com.riwi.springboot_simulacro.api.dto.request.SubmissionReq;
import com.riwi.springboot_simulacro.api.dto.response.SubmissionResp;

import java.util.List;

public interface ISubmissionService extends CrudService<SubmissionReq, SubmissionResp, Integer>{
    List<SubmissionResp> findSubmissionByAssignments(Integer assignment_id);
}
