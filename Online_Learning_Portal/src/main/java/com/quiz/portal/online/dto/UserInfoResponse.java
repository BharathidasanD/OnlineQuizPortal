package com.quiz.portal.online.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.quiz.portal.online.model.User;

public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	private List<String> roles;

	public UserInfoResponse(Long id, String username, String email, List<String> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public UserInfoResponse() {
	}

	public UserInfoResponse(User user) {
		this.id = user.getUserId();
		this.username = user.getEmail();
		this.email = user.getEmail();
		this.roles = user.getRoles().stream().map(a -> a.getUserRole().toString()).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	@Override
	public String toString() {
		return "UserInfoResponse [id=" + id + ", username=" + username + ", email=" + email + ", roles=" + roles + "]";
	}

}