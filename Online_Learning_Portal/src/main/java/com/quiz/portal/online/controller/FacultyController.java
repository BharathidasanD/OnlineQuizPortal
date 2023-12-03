package com.quiz.portal.online.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.portal.online.idgenerators.GenericIdGenerator;
import com.quiz.portal.online.model.Faculty;
import com.quiz.portal.online.service.FacultyService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FacultyController {
	private static final Logger log = LoggerFactory.getLogger(FacultyController.class);

	@Autowired
	FacultyService facultyService;
	
	@Autowired
	GenericIdGenerator idGenerator;

	@RequestMapping(value = "/addfaculty", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Faculty addNewFaculty(@RequestBody Faculty newFaculty) {
		newFaculty.setFacultyId(idGenerator.getFacultyId());
		log.info("adding faculty info....");
		Faculty retunedFac = facultyService.saveFaculty(newFaculty);
		if (retunedFac != null) {
			log.info("faculty data added sucessfully....");
		} else {
			log.info("faculty data added failure....");
		}
		return retunedFac;
	}

}
