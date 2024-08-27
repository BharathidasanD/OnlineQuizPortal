package com.quiz.portal.online.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.portal.online.dto.QuizTakingRequestStatus;
import com.quiz.portal.online.model.QuizTakingRequest;
import com.quiz.portal.online.repo.QuizTakingRequestRepo;

@Service
public class QuizTakingRequestService {

	@Autowired
	QuizTakingRequestRepo requestRepo;

	public QuizTakingRequest saveRequest(QuizTakingRequest request) {
		try {
			return requestRepo.save(request);
		} catch (Exception err) {
			return null;
		}
	}

	public List<QuizTakingRequest> getRequestByStudentId(String studentId) {
		try {
			return requestRepo.findByStudentId(studentId);
		} catch (Exception err) {
			return null;
		}
	}

	public List<QuizTakingRequestStatus> getRequestByFacultyId(String facultyId) {
		try {
			System.out.println("-------getreq====>"+facultyId);
			System.out.println(requestRepo.findByFacultyId(facultyId));
			return requestRepo.findByFacultyId(facultyId).stream().map(a -> new QuizTakingRequestStatus((String) a[0],
					(String) a[1], (String) a[2], (String) a[3], (String) a[4])).collect(Collectors.toList());

		} catch (Exception err) {
			err.printStackTrace();
			return null;
		}
	}

}
