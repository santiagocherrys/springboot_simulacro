package com.riwi.springboot_simulacro.domain.repositories;

import com.riwi.springboot_simulacro.domain.entities.User;
import com.riwi.springboot_simulacro.util.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    //List<User> findByRole(Role role);

    @Query("SELECT a FROM  user a WHERE a.role = :nombre")
    List<User> findByRole(@Param("nombre") Role role);
}
