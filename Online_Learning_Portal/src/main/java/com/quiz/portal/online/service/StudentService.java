package com.quiz.portal.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.portal.online.model.Student;
import com.quiz.portal.online.repo.StudentRepository;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepo;
	
	public Student addNewStudent(Student newStudent) {
		return studentRepo.save(newStudent);
	}
	
	public long getCount() {
		return studentRepo.count();
	}
}
