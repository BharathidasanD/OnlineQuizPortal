package com.quiz.portal.online.idgenerators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.portal.online.service.FacultyService;
import com.quiz.portal.online.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GenericIdGenerator {

	@Autowired
	FacultyService facService;
	
	@Autowired
	StudentService studentService;

	final static String prefix = "FAC";
	final static String prefix_stu = "STU";
	String formattedCount;

	public String getFacultyId() {

		long count = facService.getCount();
		count++;
		if (count < 10) {
			formattedCount = "0000" + count;
		} else if (count > 10 && count < 100) {
			formattedCount = "000" + count;
		} else if (count > 100 && count < 1000) {
			formattedCount = "00" + count;
		} else if (count > 1000 && count < 10000) {
			formattedCount = "0" + count;
		} else {
			formattedCount = String.valueOf(count);
		}
		return prefix + formattedCount;
	}
	
	public String getStudentId() {

		long count = studentService.getCount();
		count++;
		if (count < 10) {
			formattedCount = "0000" + count;
		} else if (count > 10 && count < 100) {
			formattedCount = "000" + count;
		} else if (count > 100 && count < 1000) {
			formattedCount = "00" + count;
		} else if (count > 1000 && count < 10000) {
			formattedCount = "0" + count;
		} else {
			formattedCount = String.valueOf(count);
		}
		return prefix_stu + formattedCount;
	}
}
