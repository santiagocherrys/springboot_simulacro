package com.riwi.springboot_simulacro.infrastructure.abstract_services;

import com.riwi.springboot_simulacro.api.dto.request.MessageReq;
import com.riwi.springboot_simulacro.api.dto.response.MessageResp;

public interface IMessageService extends CrudService<MessageReq, MessageResp, Integer>{
}
