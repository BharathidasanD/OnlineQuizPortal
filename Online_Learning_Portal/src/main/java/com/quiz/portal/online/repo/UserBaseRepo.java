package com.quiz.portal.online.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.portal.online.model.User;

public interface UserBaseRepo<T extends User> extends JpaRepository<T, Long> {

}
