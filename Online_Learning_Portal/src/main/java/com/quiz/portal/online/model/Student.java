package com.quiz.portal.online.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENT_INFO")
public class Student {
	@Id
	@GenericGenerator(name = "sequence_student_id", strategy = "com.quiz.portal.online.idgenerators.StudentIdGenerator")
	@GeneratedValue(generator = "sequence_student_id")
	@Column(name = "STUDENT_ID")
	private String studentId;
	@Column(name = "STUDENT_NAME")
	private String studentName;
	@Column(name = "STUDENT_EMAIL")
	private String studentEmail;
	@Column(name = "STUDENT_CONTACT")
	private String studentContact;
	@Column(name = "STUDENT_SCHOOL")
	private String studentSchool;
	@Column(name = "STUDENT_PASSWORD")
	private String studentPassword;

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

	public String getStudentPassword() {
		return studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail
				+ ", studentContact=" + studentContact + ", studentSchool=" + studentSchool + ", studentPassword="
				+ studentPassword + "]";
	}

}
