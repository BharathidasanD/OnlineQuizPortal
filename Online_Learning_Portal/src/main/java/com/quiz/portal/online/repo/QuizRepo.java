package com.quiz.portal.online.repo;

import java.util.List;
import java.util.Optional;

import org.hibernate.type.TrueFalseConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.portal.online.model.Quiz;


@Repository
public interface QuizRepo extends JpaRepository<Quiz, String> {

	public List<Quiz> findByFacultyId(String facId);
	
	@Query(value ="select A.quiz_id,A.quiz_name,A.faculty_id,B.faculty_name,B.faculty_school_name"
			+ " from quiz_info A inner join faculty_info B on A.faculty_id=B.faculty_id where( A.quiz_id=:quizId OR A.quiz_name=:quizId)",nativeQuery = true )
	public List<Object[]> getQuizByQuizId(@Param("quizId")  String quizId);
	
}
