package com.quiz.portal.online.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	private String name;
	private String contact;
	private String school;
	private Set<String> role;
	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return this.role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "SignupRequest [email=" + email + ", name=" + name + ", contact=" + contact + ", school=" + school
				+ ", role=" + role + ", password=" + password + "]";
	}
	
}