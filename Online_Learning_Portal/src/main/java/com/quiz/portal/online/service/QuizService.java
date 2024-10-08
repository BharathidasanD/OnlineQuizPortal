package com.quiz.portal.online.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.portal.online.dto.QuizSearch;
import com.quiz.portal.online.model.Quiz;
import com.quiz.portal.online.repo.QuizRepo;

@Service
public class QuizService {

	@Autowired
	QuizRepo quizRepo;
	
	public Quiz addNewQuiz(Quiz newQuiz) {
	Quiz addedQuiz=null;
	try {
	addedQuiz=quizRepo.save(newQuiz);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return addedQuiz;
	}
	
	public Optional<Quiz> getQuizById(String quizId){
		return quizRepo.findById(quizId);
	}
	public List<Quiz> getQuizByFacultyId(String facId){
		return quizRepo.findByFacultyId(facId);
	}
	
	public List<QuizSearch> getQuizByQuizId(String quizId){
		return quizRepo.getQuizByQuizId(quizId).stream()
				.map(data->new QuizSearch(data)).collect(Collectors.toList());
	}
}
