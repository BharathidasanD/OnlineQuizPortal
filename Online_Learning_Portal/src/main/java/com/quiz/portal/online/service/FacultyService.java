package com.quiz.portal.online.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.portal.online.model.Faculty;
import com.quiz.portal.online.repo.FacultyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FacultyService {
	private static final Logger log = LoggerFactory.getLogger(FacultyService.class);
	@Autowired
	FacultyRepository facultyRepo;

	public Faculty saveFaculty(Faculty newFaculty) {
		log.info("Faculty saving..");
		return facultyRepo.save(newFaculty);
	}
	
	public long getCount() {
		return facultyRepo.count();
	}
	
	public String getFacultyIdByUserId(Long userId) {
		return facultyRepo.fetchFacultyIdByUserId(userId);
	}
}
