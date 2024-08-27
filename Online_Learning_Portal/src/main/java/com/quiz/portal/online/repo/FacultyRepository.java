package com.quiz.portal.online.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.portal.online.model.Faculty;

import jakarta.transaction.Transactional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty,String> {

	@Modifying
	@Query("delete from  Faculty s where s.relatedUser.userId = :userId")
	@Transactional
	public void removeFacultyByUserId(@Param("userId") Long userId);
	
	@Query(value="select faculty_id from  faculty_info where related_user_user_id =:userId",nativeQuery = true)
	public String fetchFacultyIdByUserId(@Param("userId") Long userId);
}
