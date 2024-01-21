package com.quiz.portal.online.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.portal.online.model.TypeOfUsers;
import com.quiz.portal.online.model.UserRoles;

public interface UserRoleRepository extends JpaRepository<UserRoles, Integer>{
	
	Optional<UserRoles> findByUserRole(TypeOfUsers name);

}
