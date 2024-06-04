package com.riwi.springboot_simulacro.infrastructure.abstract_services;

import com.riwi.springboot_simulacro.api.dto.request.AssignmentReq;
import com.riwi.springboot_simulacro.api.dto.response.AssignmentResp;

import java.util.List;

public interface IAssignmentService extends CrudService<AssignmentReq, AssignmentResp, Integer>{
    public List<AssignmentResp> getAllAssignmentsByLesson(Integer id);
}
