package com.quiz.portal.online.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENT_INFO")
public class Student extends User {

	@Column(name = "STUDENT_ID", unique = true)
	private String studentId;
	@Column(name = "STUDENT_NAME")
	private String studentName;
	@Column(name = "STUDENT_EMAIL")
	private String studentEmail;
	@Column(name = "STUDENT_CONTACT")
	private String studentContact;
	@Column(name = "STUDENT_SCHOOL")
	private String studentSchool;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentContact() {
		return studentContact;
	}

	public void setStudentContact(String studentContact) {
		this.studentContact = studentContact;
	}

	public String getStudentSchool() {
		return studentSchool;
	}

	public void setStudentSchool(String studentSchool) {
		this.studentSchool = studentSchool;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail
				+ ", studentContact=" + studentContact + ", studentSchool=" + studentSchool + "]";
	}


}
