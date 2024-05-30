package com.riwi.springboot_simulacro.domain.repositories;

import com.riwi.springboot_simulacro.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
