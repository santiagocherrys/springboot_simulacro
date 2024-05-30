package com.riwi.springboot_simulacro.infrastructure.abstract_services;

import com.riwi.springboot_simulacro.api.dto.request.CourseReq;
import com.riwi.springboot_simulacro.api.dto.response.CourseResp;

public interface ICourseService extends CrudService<CourseReq, CourseResp, Integer>{
}
