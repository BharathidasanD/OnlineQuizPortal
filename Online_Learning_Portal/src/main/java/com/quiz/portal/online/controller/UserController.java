package com.quiz.portal.online.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.portal.online.dto.UserInfoResponse;
import com.quiz.portal.online.model.TypeOfUsers;
import com.quiz.portal.online.model.User;
import com.quiz.portal.online.model.UserRoles;
import com.quiz.portal.online.repo.FacultyRepository;
import com.quiz.portal.online.repo.StudentRepository;
import com.quiz.portal.online.repo.UserRoleRepository;
import com.quiz.portal.online.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200" ,maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private StudentRepository stuRepo;

	@Autowired
	UserRoleRepository roleRepository;

	@Autowired
	private FacultyRepository facRepo;

	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@GetMapping("/allusers")
	public List<UserInfoResponse> getAllUsers() {
		List<UserInfoResponse> users = null;
		try {
			users = userService.getAllUsers().stream().map(a -> new UserInfoResponse(a)).collect(Collectors.toList());
		} catch (Exception err) {
			log.info("Something went wrong-->" + err);
		}
		return users;
	}

	@GetMapping("/user/{userid}")
	public UserInfoResponse getAllUsers(@PathVariable("userid") Long userID) {
		UserInfoResponse users = null;
		try {
			users = new UserInfoResponse(userService.getUserById(userID));
			// .stream().map(a -> new UserInfoResponse(a)).collect(Collectors.toList());
		} catch (Exception err) {
			log.info("Something went wrong-->" + err);
		}
		return users;
	}

	@PostMapping("/updateuser")
	public UserInfoResponse UpdateUsers(@RequestBody UserInfoResponse user) {
		log.info("Userinfo response-->" + user);
		User luser = null;
		Set<UserRoles> roles = new HashSet<>();
		try {
			luser = userService.getUserById(user.getId());
			// .stream().map(a -> new UserInfoResponse(a)).collect(Collectors.toList());
			log.info("User data" + luser);
			luser.setEmail(user.getEmail());

			user.getRoles().forEach(role -> {
				switch (role) {
				case "ROLE_ADMIN":
					UserRoles adminRole = roleRepository.findByUserRole(TypeOfUsers.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role admin is not found."));
					roles.add(adminRole);
					break;
				case "ROLE_FACULTY":
					UserRoles modRole = roleRepository.findByUserRole(TypeOfUsers.ROLE_FACULTY)
							.orElseThrow(() -> new RuntimeException("Error: Role faculty is not found."));
					roles.add(modRole);
					break;
				case "ROLE_STUDENT":
					UserRoles userRole = roleRepository.findByUserRole(TypeOfUsers.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role student is not found."));
					roles.add(userRole);
				}
			});

			luser.setRoles(roles);
			userService.saveUser(luser);

		} catch (Exception err) {
			log.info("Something went wrong-->" + err);
		}
		return new UserInfoResponse(luser);
	}

	@DeleteMapping("/removeuser/{userid}")
	public String removeUser(@PathVariable("userid") Long userID) {
		String deleteStatus = Long.toString(userID);
		try {
			facRepo.removeFacultyByUserId(userID);
			stuRepo.removeStudentByUserId(userID);
			userService.removeUserById(userID);
			deleteStatus = deleteStatus.concat("Deleted Sucessfully");
		} catch (Exception err) {
			err.printStackTrace();
			log.error("Facing issue while delete-->" + err);
			deleteStatus = deleteStatus.concat("Deletion Failure");
		}

		return deleteStatus;
	}

}
