package com.riwi.springboot_simulacro.infrastructure.abstract_services;

import com.riwi.springboot_simulacro.domain.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IUserservicePrueba {
    public User save(User objUser);
    public List<User> getAll();
    public Page<User>findAllPaginate(int page, int size);

    public void delete(Integer id);

    public User update(User objUser);

    public User getById(Integer id);
}
