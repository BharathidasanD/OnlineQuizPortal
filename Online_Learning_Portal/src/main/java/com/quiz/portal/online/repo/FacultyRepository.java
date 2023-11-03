package com.quiz.portal.online.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.portal.online.model.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, String> {

	
}
