package com.quiz.portal.online.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_ROLES")
public class UserRoles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userRoleId;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private TypeOfUsers userRole;
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	public TypeOfUsers getUserRole() {
		return userRole;
	}
	public void setUserRole(TypeOfUsers userRole) {
		this.userRole = userRole;
	}
	@Override
	public String toString() {
		return "UserRoles [userRoleId=" + userRoleId + ", userRole=" + userRole + "]";
	}
	
	
}


