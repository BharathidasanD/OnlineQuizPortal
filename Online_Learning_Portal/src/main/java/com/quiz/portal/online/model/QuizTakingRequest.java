package com.quiz.portal.online.model;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz_attending_request")
public class QuizTakingRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long requestId;
	private String quizId;
	private String studentId;
	private String requestStatus;
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	@Override
	public String toString() {
		return "QuizTakingRequest [requestId=" + requestId + ", quizId=" + quizId + ", studentId=" + studentId
				+ ", requestStatus=" + requestStatus + "]";
	}
	
	
}
