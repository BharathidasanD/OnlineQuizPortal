package com.quiz.portal.online.dto;

public class QuizSearch {
	
	private String quizId;
	private String quizName;
	private String facultyId;
	private String facultyName;
	private String facultySchoolName;
	
	
	public QuizSearch(Object[] listOfQuiz ) {
	this.quizId=(String) listOfQuiz[0];
	this.quizName=(String) listOfQuiz[1];
	this.facultyId=(String) listOfQuiz[2];
	this.facultyName=(String) listOfQuiz[3];
	this.facultySchoolName=(String) listOfQuiz[4];
	
	
	}
	public QuizSearch() {
		super();
	}
	public String getQuizId() {
		return quizId;
	}
	public void setQuizId(String quizId) {
		this.quizId = quizId;
	}
	public String getQuizName() {
		return quizName;
	}
	public void setQuizName(String quizName) {
		this.quizName = quizName;
	}
	public String getFacultyId() {
		return facultyId;
	}
	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public String getFacultySchoolName() {
		return facultySchoolName;
	}
	public void setFacultySchoolName(String facultySchoolName) {
		this.facultySchoolName = facultySchoolName;
	}
	@Override
	public String toString() {
		return "QuizSearch [quizId=" + quizId + ", quizName=" + quizName + ", facultyId=" + facultyId + ", facultyName="
				+ facultyName + ", facultySchoolName=" + facultySchoolName + "]";
	}
	
	

}
