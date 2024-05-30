package com.riwi.springboot_simulacro.infrastructure.services;

import com.riwi.springboot_simulacro.domain.entities.User;
import com.riwi.springboot_simulacro.domain.repositories.UserRepository;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.IUserservicePrueba;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserservicePrueba {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public User save(User objUser) {
        return this.userRepository.save(objUser);
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public Page<User> findAllPaginate(int page, int size) {
        /*Validar que la página no sea menor a 0*/
        if(page < 0){
            page = 0;
        }

        /*Crear la paginación*/
        Pageable objPage = PageRequest.of(page, size);

        return this.userRepository.findAll(objPage);
    }

    @Override
    public void delete(Integer id) {
        //se busca el elemento a borrar por id
        var user = this.userRepository.findById(id).orElseThrow();

        //se borra el elemento
        this.userRepository.delete(user);
    }

    @Override
    public User update(User objUser) {
        return this.userRepository.save(objUser);
    }

    @Override
    public User getById(Integer id) {
        return this.userRepository.findById(id).orElseThrow();
    }

}


