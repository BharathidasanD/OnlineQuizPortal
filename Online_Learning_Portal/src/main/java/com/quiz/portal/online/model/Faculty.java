package com.quiz.portal.online.model;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FACULTY_TABLE")
public class Faculty {

	@Id
	@GenericGenerator(name = "sequence_faculty_id", strategy = "com.quiz.portal.online.idgenerators.FacultyIdGenerator")
	@GeneratedValue(generator = "sequence_faculty_id")
	@Column(name = "FACULTY_ID")
	private String facultyId;
	private String facultyName;
	@Column(name = "FACULTY_EMAIL", unique = true, nullable = false)
	private String facultyEmail;
	@Column(name = "FACULTY_CONTACT", unique = true, nullable = false)
	private String facultyContact;
	private String facultySchoolName;
	private String facultyPassword;

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
		this.facultyPassword = facultyPassword;
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

	public String getFacultyPassword() {
		return facultyPassword;
	}

	public void setFacultyPassword(String facultyPassword) {
		this.facultyPassword = facultyPassword;
	}

	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + ", facultyEmail=" + facultyEmail
				+ ", facultyContact=" + facultyContact + ", facultySchoolName=" + facultySchoolName
				+ ", facultyPassword=" + facultyPassword + "]";
	}

}
