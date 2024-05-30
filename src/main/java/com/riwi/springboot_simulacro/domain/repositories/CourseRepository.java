package com.riwi.springboot_simulacro.domain.repositories;

import com.riwi.springboot_simulacro.domain.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
