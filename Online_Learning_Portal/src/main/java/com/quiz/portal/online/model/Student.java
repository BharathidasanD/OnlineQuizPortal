package com.quiz.portal.online.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "STUDENT_INFO")
public class Student {

	@Id
	@GenericGenerator(name = "sequence_student_id", strategy = "com.quiz.portal.online.idgenerators.StudentIdGenerator")
	@GeneratedValue(generator = "sequence_student_id")
	@Column(name = "STUDENT_ID", unique = true)
	private String studentId;
	@Column(name = "STUDENT_NAME")
	private String studentName;
	@Column(name = "STUDENT_CONTACT")
	private String studentContact;
	@Column(name = "STUDENT_SCHOOL")
	private String studentSchool;
	@OneToOne(optional = false,cascade = CascadeType.ALL)
	private User relatedUser;

	public String getStudentId() {
		return studentId;
	}

	public User getRelatedUser() {
		return relatedUser;
	}

	public void setRelatedUser(User relatedUser) {
		this.relatedUser = relatedUser;
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
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentContact=" + studentContact
				+ ", studentSchool=" + studentSchool + ", relatedUser=" + relatedUser + "]";
	}

	

}
