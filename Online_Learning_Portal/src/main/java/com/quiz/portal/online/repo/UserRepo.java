package com.quiz.portal.online.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.portal.online.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
