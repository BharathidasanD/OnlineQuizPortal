package com.quiz.portal.online.controller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.portal.online.model.Faculty;
import com.quiz.portal.online.model.TypeOfUsers;
import com.quiz.portal.online.model.User;
import com.quiz.portal.online.model.UserRoles;
import com.quiz.portal.online.repo.UserRoleRepository;
import com.quiz.portal.online.service.FacultyService;
import com.quiz.portal.online.service.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/faculty")
@Slf4j
public class FacultyController {
	private static final Logger log = LoggerFactory.getLogger(FacultyController.class);

	@Autowired
	UserRoleRepository roleRepository;

	@Autowired
	FacultyService facultyService;

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/factest")
	public String testfac(){
		return "fac test";
	}
	
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/addfaculty")
	public Faculty addNewFaculty(@RequestBody Faculty newFaculty) {	
		log.info("adding faculty info...." + newFaculty);
		User savedUser = newFaculty.getRelatedUser();
		savedUser.setPassword(encoder.encode(savedUser.getPassword()));
		UserRoles modRole = roleRepository.findByUserRole(TypeOfUsers.ROLE_FACULTY)
				.orElseThrow(() -> new RuntimeException("Error: Role faculty is not found."));
		Set<UserRoles> temp = new HashSet<UserRoles>();
		temp.add(modRole);
		savedUser.setRoles(temp);
		savedUser = userService.saveUser(savedUser);
		newFaculty.setRelatedUser(savedUser);
		Faculty retunedFac = facultyService.saveFaculty(newFaculty);
		if (retunedFac != null) {
			log.info("faculty data added sucessfully....");
		} else {
			log.info("faculty data added failure....");
		}
		return retunedFac;
	}
	@GetMapping("/getfacultyid/{userid}")
	public String getFacultyIdByUserId(@PathVariable("userid") Long userId) {
		
		return facultyService.getFacultyIdByUserId(userId);
	}
	
}
