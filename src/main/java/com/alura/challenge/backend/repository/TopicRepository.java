package com.alura.challenge.backend.repository;

import com.alura.challenge.backend.entities.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Optional<Topic> findByTitleAndMessage(String title, String message);

    @Query("SELECT t FROM Topic t WHERE LOWER(t.course.name) like %:courseName% AND FUNCTION('YEAR', t.creationDate) = :year")
    Page<Topic> findByCourseNameAndYear(String courseName, int year, Pageable pageable);

}
