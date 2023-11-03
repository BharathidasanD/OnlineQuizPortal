package com.quiz.portal.online.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.portal.online.model.QuizQuestions;
import com.quiz.portal.online.service.QuizQuestionsService;

@RestController
public class QuizQuestionsController {
	
	@Autowired
	QuizQuestionsService quizQuestionService;

	@PostMapping("/addnewquestion")
	public QuizQuestions addNewQuizQuestion(@RequestBody QuizQuestions newQuestion) {
		System.out.println(newQuestion);
		return quizQuestionService.addNewQuestion(newQuestion);
		
	}
	
	@PostMapping("/addnewlistofquestion")
	public List<QuizQuestions> addNewListOfQuizQuestion(@RequestBody List<QuizQuestions> newQuestions) {
		return quizQuestionService.addNewListOfQuestions(newQuestions);
		
	}
	
	@GetMapping("/getallquestions")
	public List<QuizQuestions> getAllQuestions(){
		return quizQuestionService.getAllQuestions();
	}
}
