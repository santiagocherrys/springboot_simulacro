package com.riwi.springboot_simulacro.infrastructure.abstract_services;

import com.riwi.springboot_simulacro.api.dto.request.SubmissionReq;
import com.riwi.springboot_simulacro.api.dto.response.SubmissionResp;

public interface ISubmissionService extends CrudService<SubmissionReq, SubmissionResp, Integer>{
}
