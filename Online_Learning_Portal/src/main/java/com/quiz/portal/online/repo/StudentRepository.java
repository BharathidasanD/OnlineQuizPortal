package com.quiz.portal.online.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.portal.online.model.Student;

import jakarta.transaction.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

	@Modifying
	@Query("delete from Student s where s.relatedUser.userId = :userId")
	@Transactional
	public void removeStudentByUserId(@Param("userId") Long userId);

	@Query(value="select student_id from  student_info where related_user_user_id =:userId",nativeQuery = true)
	public String fetchStudentIdByUserId(Long userId);
}
