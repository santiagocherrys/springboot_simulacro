package com.riwi.springboot_simulacro.infrastructure.abstract_services;

import com.riwi.springboot_simulacro.api.dto.request.UserReq;
import com.riwi.springboot_simulacro.api.dto.response.UserResp;

public interface IUserService extends CrudService<UserReq, UserResp, Integer>{
}
