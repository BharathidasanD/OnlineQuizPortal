package com.quiz.portal.online.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "FACULTY_INFO")
public class Faculty extends User {

	@Column(name = "FACULTY_ID", unique = true)
	private String facultyId;
	@Column(name = "FACULTY_NAME")
	private String facultyName;
	@Column(name = "FACULTY_EMAIL", unique = true, nullable = false)
	private String facultyEmail;
	@Column(name = "FACULTY_CONTACT", unique = true, nullable = false)
	private String facultyContact;
	@Column(name = "FACULTY_SCHOOL_NAME", unique = true, nullable = false)
	private String facultySchoolName;

	public String getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}

	public Faculty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Faculty(String facultyId, String facultyName, String facultyEmail, String facultyContact,
			String facultySchoolName, String facultyPassword) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.facultyEmail = facultyEmail;
		this.facultyContact = facultyContact;
		this.facultySchoolName = facultySchoolName;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getFacultyEmail() {
		return facultyEmail;
	}

	public void setFacultyEmail(String facultyEmail) {
		this.facultyEmail = facultyEmail;
	}

	public String getFacultyContact() {
		return facultyContact;
	}

	public void setFacultyContact(String facultyContact) {
		this.facultyContact = facultyContact;
	}

	public String getFacultySchoolName() {
		return facultySchoolName;
	}

	public void setFacultySchoolName(String facultySchoolName) {
		this.facultySchoolName = facultySchoolName;
	}

	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + ", facultyEmail=" + facultyEmail
				+ ", facultyContact=" + facultyContact + ", facultySchoolName=" + facultySchoolName + "]";
	}

}
