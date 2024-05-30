package com.riwi.springboot_simulacro.domain.repositories;

import com.riwi.springboot_simulacro.domain.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
}
