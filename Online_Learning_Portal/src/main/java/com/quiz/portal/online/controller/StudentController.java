package com.quiz.portal.online.controller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.portal.online.idgenerators.GenericIdGenerator;
import com.quiz.portal.online.model.Student;
import com.quiz.portal.online.model.TypeOfUsers;
import com.quiz.portal.online.model.User;
import com.quiz.portal.online.model.UserRoles;
import com.quiz.portal.online.repo.UserRoleRepository;
import com.quiz.portal.online.service.StudentService;
import com.quiz.portal.online.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/student")
public class StudentController {
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	StudentService studentService;

	@Autowired
	UserRoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	GenericIdGenerator idGenerator;

	@Autowired
	UserService userService;

	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN' , 'ROLE_FACULTY')")
	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody Student newStudent) {

		log.info("adding student info...." + newStudent);
		User savedUser = newStudent.getRelatedUser();
		savedUser.setPassword(encoder.encode(savedUser.getPassword()));
		UserRoles modRole = roleRepository.findByUserRole(TypeOfUsers.ROLE_STUDENT)
				.orElseThrow(() -> new RuntimeException("Error: Role student is not found."));
		Set<UserRoles> temp = new HashSet<UserRoles>();
		temp.add(modRole);
		savedUser.setRoles(temp);
		savedUser = userService.saveUser(savedUser);
		newStudent.setRelatedUser(savedUser);
		Student retunedFac = studentService.addNewStudent(newStudent);
		if (retunedFac != null) {
			log.info("student data added sucessfully....");
		} else {
			log.info("student data added failure....");
		}
		return retunedFac;
	}

}
