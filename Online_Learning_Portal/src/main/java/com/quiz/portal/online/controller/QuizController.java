package com.quiz.portal.online.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.portal.online.model.Quiz;
import com.quiz.portal.online.model.QuizQuestions;
import com.quiz.portal.online.service.QuizQuestionsService;
import com.quiz.portal.online.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizController {
	private static final Logger log = LogManager.getLogger(QuizController.class);
	@Autowired
	QuizService quizService;

	@Autowired
	QuizQuestionsService quizQuestionService;

//	@Autowired
//	OptionRepo optionRepo;

	@PostMapping("/addquiz")
	public Quiz addQuiz(@RequestBody Quiz newQuiz) {
		log.info("Recived Quiz Object---->" + newQuiz);
		Quiz addedQuiz = new Quiz();
		List<QuizQuestions> listOfQuestions = new ArrayList<QuizQuestions>();
//		List<Option> listOfOptions = new ArrayList<Option>();
		addedQuiz.setFacultyId(newQuiz.getFacultyId());
		addedQuiz.setQuizName(newQuiz.getQuizName());
//		QuizQuestions tempQuestion=new QuizQuestions();
//		Option tempOption=new Option();

//		try {
		addedQuiz = quizService.addNewQuiz(addedQuiz);
//			
//			
		for (QuizQuestions question : newQuiz.getListOfQuestions()) {
			// log.info("Before Adding Quiz question->" + question);
			question.setQuestionBelongsTo(addedQuiz);
			// log.info("After Adding Quiz question->" + question);
			listOfQuestions.add(question);

		}
		// log.info("list of options====>"+listOfOptions);
		// optionRepo.saveAll(listOfOptions);
//			listOfQuestions=quizQuestionService.addNewListOfQuestions(listOfQuestions);
//			for(QuizQuestions question:listOfQuestions) {
//				
//			}
		quizQuestionService.addNewListOfQuestions(listOfQuestions);
//			//log.info("Mapped Quiz--->"+addedQuiz);
//		} catch (Exception err) {
//			err.printStackTrace();
//		}
		log.info("addedQuiz from database-->" + addedQuiz);
		return addedQuiz;

	}

	@GetMapping("/getquiz/{quizId}")
	public Quiz getQuizById(@PathVariable("quizId") String quizId) {
		log.info("fetched quiz------" + quizService.getQuizById(quizId));
		return quizService.getQuizById(quizId).orElse(null);

	}
	
	@GetMapping("/myquiz/{facId}")
	public List<Quiz> getQuizByFacultyId(@PathVariable("facId") String facId) {
		log.info("faculty id===>"+facId);
		log.info("fetched quiz------" + quizService.getQuizByFacultyId(facId));
		return quizService.getQuizByFacultyId(facId);

	}
	

}
