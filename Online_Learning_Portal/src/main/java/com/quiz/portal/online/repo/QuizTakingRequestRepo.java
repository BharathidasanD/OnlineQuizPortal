package com.quiz.portal.online.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.portal.online.model.QuizTakingRequest;

@Repository
public interface QuizTakingRequestRepo  extends JpaRepository<QuizTakingRequest, Long>{

	public List<QuizTakingRequest> findByStudentId(String studentId);
	
	@Query(nativeQuery = true,value="select A.quiz_id,A.student_id,A.request_status,B.faculty_id, B.quiz_name "
			+ " from quiz_attending_request A inner join quiz_info B on A.quiz_id=B.quiz_id where "
			+ " B.faculty_id=:facultyId"
			)
	public List<Object[]> findByFacultyId(@Param("facultyId") String facultyId);
}
