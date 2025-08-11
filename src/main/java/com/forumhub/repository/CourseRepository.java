package com.forumhub.repository;

import com.forumhub.domain.curso.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
