package com.riwi.springboot_simulacro.infrastructure.abstract_services;

import com.riwi.springboot_simulacro.api.dto.request.EnrollmentReq;
import com.riwi.springboot_simulacro.api.dto.response.EnrollmentResp;

public interface IEnrollmentService extends CrudService<EnrollmentReq, EnrollmentResp, Integer>{
}
