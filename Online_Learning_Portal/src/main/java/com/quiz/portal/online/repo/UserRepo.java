package com.quiz.portal.online.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.portal.online.model.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);

//	@Query("select A.user_id,A.email,C.user_role from user_info A inner join user_roles_type B on A.user_id=B.user_id inner join user_roles C on B.user_role_id=C.user_role_id\r\n"
//			+ "where A.user_id=:userId;")
//	public List<Object> getUsersByUserId(@Param("userId") Long userId);
}
