package com.quiz.portal.online.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.portal.online.model.QuizQuestions;
import com.quiz.portal.online.repo.QuizQuestionsRepo;

@Service
public class QuizQuestionsService {
	
	@Autowired
	QuizQuestionsRepo quizQuestionRepo;
	
	public QuizQuestions addNewQuestion(QuizQuestions newQuestions) {
		return quizQuestionRepo.save(newQuestions);
		
	}
	
	public List<QuizQuestions> addNewListOfQuestions(List<QuizQuestions> listOfQuestions){
		return quizQuestionRepo.saveAll(listOfQuestions);
		
	}
	
	public List<QuizQuestions> getAllQuestions(){
		return quizQuestionRepo.findAll();
	}

}
