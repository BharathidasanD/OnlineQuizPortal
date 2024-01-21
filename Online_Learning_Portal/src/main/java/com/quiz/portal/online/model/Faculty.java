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
@Table(name = "FACULTY_INFO")
public class Faculty {
	@Id
	@GenericGenerator(name = "sequence_faculty_id", strategy = "com.quiz.portal.online.idgenerators.FacultyIdGenerator")
	@GeneratedValue(generator = "sequence_faculty_id")
	@Column(name = "FACULTY_ID", unique = true)
	private String facultyId;
	@Column(name = "FACULTY_NAME")
	private String facultyName;
	@Column(name = "FACULTY_CONTACT", unique = true, nullable = false)
	private String facultyContact;
	@Column(name = "FACULTY_SCHOOL_NAME", nullable = false)
	private String facultySchoolName;
	@OneToOne(optional = false,cascade = CascadeType.ALL)
	private User relatedUser;

	public String getFacultyId() {
		return facultyId;
	}

	public User getRelatedUser() {
		return relatedUser;
	}

	public void setRelatedUser(User relatedUser) {
		this.relatedUser = relatedUser;
	}

	public void setFacultyId(String facultyId) {
		this.facultyId = facultyId;
	}

	public Faculty() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Faculty(String facultyId, String facultyName, String facultyContact,
			String facultySchoolName) {
		super();
		this.facultyId = facultyId;
		this.facultyName = facultyName;
		this.facultyContact = facultyContact;
		this.facultySchoolName = facultySchoolName;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
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
		return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + ", facultyContact=" + facultyContact
				+ ", facultySchoolName=" + facultySchoolName + ", relatedUser=" + relatedUser + "]";
	}

	

}
