package com.bezkoder.springjwt.payload.request;

import java.util.Set;

import jakarta.validation.constraints.*;

public class SignupRequest {
	@NotBlank
	private String schoolName;
	
	@NotBlank
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	private Set<String> role;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	@NotBlank
	@Size(max = 1)
	private String s_grade;

	@NotBlank
	@Size(max = 2)
	private String s_class;
	
	@NotBlank
	@Size(max = 2)
	private String s_number;
	
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getS_grade() {
		return s_grade;
	}

	public void setS_grade(String s_grade) {
		this.s_grade = s_grade;
	}

	public String getS_class() {
		return s_class;
	}

	public void setS_class(String s_class) {
		this.s_class = s_class;
	}
	
	public String getS_number() {
		return s_number;
	}
	
	public void setS_number(String s_number) {
		this.s_number = s_number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
}
