package com.quiz.portal.online.dto;

public class QuizTakingRequestStatus {

	private String quizId;
	private String studentId;
	private String requestStatus;
	private String facultyId;
	private String quizName;
	
	
	public QuizTakingRequestStatus() {
		super();
	}
	public QuizTakingRequestStatus(String quizId, String studentId, String requestStatus, String facultyId,
			String quizName) {
		super();
		this.quizId = quizId;
		this.studentId = studentId;
		this.requestStatus = requestStatus;
		this.facultyId = facultyId;
		this.quizName = quizName;
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
	public String getfacultyId() {
		return facultyId;
	}
	public void setfacultyId(String facultyId) {
		this.facultyId = facultyId;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	@Override
	public String toString() {
		return "QuizTakingRequestStatus [quizId=" + quizId + ", studentId=" + studentId + ", requestStatus="
				+ requestStatus + ", facultyId=" + facultyId + ", quizName=" + quizName + "]";
	}
	
	
}
