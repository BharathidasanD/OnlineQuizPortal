package com.quiz.portal.online.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.portal.online.model.Student;
import com.quiz.portal.online.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class StudentController {
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	StudentService studentService;

	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student newStudent) {

		log.info("adding Student info....");
		Student retunedStudent = studentService.addNewStudent(newStudent);
		if (retunedStudent != null) {
			log.info("student data added sucessfully....");
		} else {
			log.info("student data added failure....");
		}
		return retunedStudent;
	}

}
