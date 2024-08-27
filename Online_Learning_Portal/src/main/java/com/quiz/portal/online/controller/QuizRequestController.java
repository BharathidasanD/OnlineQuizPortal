package com.quiz.portal.online.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.portal.online.dto.QuizTakingRequestStatus;
import com.quiz.portal.online.model.QuizTakingRequest;
import com.quiz.portal.online.service.QuizTakingRequestService;

@RestController()
@RequestMapping("/api/quizrequest")
@CrossOrigin(origins = "http://localhost:4200")
public class QuizRequestController {
	private static final Logger log = LoggerFactory.getLogger(QuizRequestController.class);
	@Autowired
	QuizTakingRequestService requestService;

	@PostMapping("/request")
	public QuizTakingRequest newRequest(@RequestBody QuizTakingRequest newRequest) {
		try {
			log.info("Recived request" + newRequest);
			return requestService.saveRequest(newRequest);
		} catch (Exception err) {
			log.error("Facing issue while sending a request for this quiz..");
			return null;
		}

	}

	@GetMapping("/status/{studentid}")
	public List<QuizTakingRequest> getQuizRequest(@PathVariable("studentid") String studentId) {
		try {
			return this.requestService.getRequestByStudentId(studentId);
		} catch (Exception err) {
			log.error("Exception while fetcghing");
			return null;
		}
	}

	@PostMapping("/managerequest")
	public QuizTakingRequest manageRequest(@RequestBody QuizTakingRequest manageRequest) {
		try {
			log.info("Recived request--->" + manageRequest);
			return requestService.saveRequest(manageRequest);
		} catch (Exception err) {
			log.error("Facing exception while managing request....");
			return null;
		}
	}

	@GetMapping("/myrequests/{facultyId}")
	public List<QuizTakingRequestStatus> viewRequest(@PathVariable("facultyId") String facultyId) {
		try {

			return requestService.getRequestByFacultyId(facultyId);
		} catch (Exception err) {
			log.error("Facing exception while managing request....");
			return null;
		}
	}
}
