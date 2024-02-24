package com.quiz.portal.online.service;

import java.util.List;

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
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public User getUserById(long userId){
		return userRepo.findById(userId).orElse(null);
	}
	
	public void removeUserById(Long userId) {
		try {
		 userRepo.deleteById(userId);
		}
		catch(Exception err) {
			throw err;
		}
	}

}
