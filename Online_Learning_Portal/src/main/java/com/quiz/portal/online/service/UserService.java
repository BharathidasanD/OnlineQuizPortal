package com.quiz.portal.online.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.portal.online.model.User;
import com.quiz.portal.online.repo.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;

	public User saveUser(User newUser) {
		return userRepo.save(newUser);

	}

}
