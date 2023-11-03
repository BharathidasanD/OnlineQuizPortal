package com.quiz.portal.online.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.portal.online.model.QuizQuestions;

public interface QuizQuestionsRepo extends JpaRepository<QuizQuestions, Long> {

}
