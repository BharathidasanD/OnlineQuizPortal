package com.quiz.portal.online.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.portal.online.dto.MessageResponse;
import com.quiz.portal.online.dto.NewUser;
import com.quiz.portal.online.dto.SignupRequest;
import com.quiz.portal.online.dto.UserInfoResponse;
import com.quiz.portal.online.model.Faculty;
import com.quiz.portal.online.model.Student;
import com.quiz.portal.online.model.TypeOfUsers;
import com.quiz.portal.online.model.User;
import com.quiz.portal.online.model.UserRoles;
import com.quiz.portal.online.repo.FacultyRepository;
import com.quiz.portal.online.repo.StudentRepository;
import com.quiz.portal.online.repo.UserRepo;
import com.quiz.portal.online.repo.UserRoleRepository;
import com.quiz.portal.online.service.FacultyService;
import com.quiz.portal.online.service.StudentService;
import com.quiz.portal.online.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
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
	UserRepo userRepository;

	@Autowired
	UserRoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	FacultyService facService;

	@Autowired
	StudentService studentService;

	Student newStu=null;
	Faculty newFac=null;

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

	@PostMapping("/adduser")
	public ResponseEntity<?> registerUser( @RequestBody NewUser signUpRequest) {
		
		log.info("Recived add user data-->" + signUpRequest);

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<UserRoles> roles = new HashSet<>();

		if (strRoles == null) {
			UserRoles userRole = roleRepository.findByUserRole(TypeOfUsers.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role user is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ROLE_FACULTY":
					log.info("created faculty....");
					UserRoles modRole = roleRepository.findByUserRole(TypeOfUsers.ROLE_FACULTY)
							.orElseThrow(() -> new RuntimeException("Error: Role faculty is not found."));
					roles.add(modRole);
					newFac = new Faculty();
					newFac.setFacultyContact(signUpRequest.getContact());
					newFac.setFacultyName(signUpRequest.getName());
					newFac.setFacultySchoolName(signUpRequest.getSchool());
					break;
				case "ROLE_STUDENT":
					UserRoles userRole = roleRepository.findByUserRole(TypeOfUsers.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role student is not found."));
					roles.add(userRole);
					newStu = new Student();
					newStu.setStudentContact(signUpRequest.getContact());
					newStu.setStudentName(signUpRequest.getName());
					newStu.setStudentSchool(signUpRequest.getSchool());
					break;
				}
			});
		}

		user.setRoles(roles);
		User tempUser = userRepository.save(user);
		if (newFac != null) {
			newFac.setRelatedUser(tempUser);
			facService.saveFaculty(newFac);
			log.info("Faculty saved...");
			log.info("clear cache faculty");
			newFac=null;
		}
		if (newStu != null) {
			newStu.setRelatedUser(tempUser);
			studentService.addNewStudent(newStu);
			log.info("student saved..");
			log.info("clear cache student");
			newStu=null;
		}
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
