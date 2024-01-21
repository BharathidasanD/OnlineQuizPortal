package com.quiz.portal.online.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.portal.online.controller.FacultyController;
import com.quiz.portal.online.model.User;
import com.quiz.portal.online.repo.UserRepo;

@Service
public class UserDetailsServiceImpl  implements UserDetailsService{

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	 @Autowired
	  UserRepo userRepository;

	  @Override
	  @Transactional
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    User user = userRepository.findByEmail(username)
	        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

//	    log.info("Fetched user info--->"+user);
//	    log.info("structured user--->"+UserDetailsImpl.build(user));
	    return UserDetailsImpl.build(user);
	  }
}
