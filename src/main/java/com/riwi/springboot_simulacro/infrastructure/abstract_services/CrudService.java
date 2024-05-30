package com.riwi.springboot_simulacro.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

//RQ -> Request
//RS -> Response
//ID -> Tipo de dato de la llave primaria de la entidad
public interface CrudService<RQ,RS,ID> {
    public void delete(ID id);
    public RS create(RQ request);
    public RS update(ID id, RQ request);
    public Page<RS> getAll(int page, int size);
    public RS getById(ID id);
}
