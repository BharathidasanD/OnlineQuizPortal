package com.quiz.portal.online.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_info")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long userId;
	protected String password;
	protected String email;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USER_ROLES_TYPE", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "userRoleId"))
	private Set<UserRoles> roles = new HashSet<>();

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User( String email, String password) {
		super();
		this.password = password;
		this.email = email;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserRoles> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRoles> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", email=" + email + ", roles=" + roles + "]";
	}
	

}
