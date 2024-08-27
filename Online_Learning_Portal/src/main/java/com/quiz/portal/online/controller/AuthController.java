package com.quiz.portal.online.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.portal.online.dto.LoginRequest;
import com.quiz.portal.online.dto.MessageResponse;
import com.quiz.portal.online.dto.SignupRequest;
import com.quiz.portal.online.dto.UserInfoResponse;
import com.quiz.portal.online.model.Faculty;
import com.quiz.portal.online.model.Student;
import com.quiz.portal.online.model.TypeOfUsers;
import com.quiz.portal.online.model.User;
import com.quiz.portal.online.model.UserRoles;
import com.quiz.portal.online.repo.UserRepo;
import com.quiz.portal.online.repo.UserRoleRepository;
import com.quiz.portal.online.security.jwt.JwtUtils;
import com.quiz.portal.online.security.service.UserDetailsImpl;
import com.quiz.portal.online.service.FacultyService;
import com.quiz.portal.online.service.StudentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200" ,maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	AuthenticationManager authenticationManager;

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

	@Autowired
	JwtUtils jwtUtils;

	Student newStu=null;
	Faculty newFac=null;
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest.getUsername() + " " + loginRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
				new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getUsername(), roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		log.info("Recived data-->"+signUpRequest);
		
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

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
				case "admin":
					UserRoles adminRole = roleRepository.findByUserRole(TypeOfUsers.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role admin is not found."));
					roles.add(adminRole);
					break;
				case "faculty":
					UserRoles modRole = roleRepository.findByUserRole(TypeOfUsers.ROLE_FACULTY)
							.orElseThrow(() -> new RuntimeException("Error: Role faculty is not found."));
					roles.add(modRole);
					newFac=new  Faculty();
					newFac.setFacultyContact(signUpRequest.getContact());
					newFac.setFacultyName(signUpRequest.getName());
					newFac.setFacultySchoolName(signUpRequest.getSchool());
					break;
				default:
					UserRoles userRole = roleRepository.findByUserRole(TypeOfUsers.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role student is not found."));
					roles.add(userRole);
					newStu=new Student();
					newStu.setStudentContact(signUpRequest.getContact());
					newStu.setStudentName(signUpRequest.getName());
					newStu.setStudentSchool(signUpRequest.getSchool());
				}
			});
		}

		user.setRoles(roles);
		User tempUser=userRepository.save(user);
		if(newFac !=null) {
			newFac.setRelatedUser(tempUser);
			facService.saveFaculty(newFac);
			log.info("clear cache faculty");
			newFac=null;
		}
		if(newStu!=null) {
			newStu.setRelatedUser(tempUser);
			studentService.addNewStudent(newStu);
			log.info("clear cache student.");
			newStu=null;
			
		}
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping("/admin")
	public String getValue() {
		return "admin content";
	}
	
	@PreAuthorize("hasAuthority('ROLE_FACULTY')")
	@PostMapping("/facu")
	public String getFacValue() {
		return "faculty content";
	}

	
	@PreAuthorize("hasAuthority('ROLE_STUDENT')")
	@PostMapping("/student")
	public String getStuValue() {
		return "studnet content";
	}
	
	@PreAuthorize("hasAuthority('ROLE_TEST')")
	@PostMapping("/test")
	public String getTesValue() {
		return "test content";
	}
}
