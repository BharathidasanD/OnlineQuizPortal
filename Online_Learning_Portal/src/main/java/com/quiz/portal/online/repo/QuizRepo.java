package com.quiz.portal.online.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.portal.online.model.Quiz;

public interface QuizRepo extends JpaRepository<Quiz, String> {

	public List<Quiz> findByFacultyId(String facId);
	
}
