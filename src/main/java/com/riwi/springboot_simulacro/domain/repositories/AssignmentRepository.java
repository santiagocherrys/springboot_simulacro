package com.riwi.springboot_simulacro.domain.repositories;

import com.riwi.springboot_simulacro.domain.entities.Assignment;
import com.riwi.springboot_simulacro.domain.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Integer> {

    //@Query("SELECT a FROM assignment a WHERE a.lesson_id = :lessonId")
    //List<Assignment> findByLesson_id(@Param("lessonId") Integer lesson_id);

    //@Query("SELECT t FROM assignment t WHERE t.description = ?1")
    //List<Assignment> findByDescription(String description);

    List<Assignment> findByLesson(Lesson lesson);
}
