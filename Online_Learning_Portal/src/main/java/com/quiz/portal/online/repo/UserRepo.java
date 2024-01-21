package com.quiz.portal.online.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.portal.online.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
