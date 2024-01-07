package com.bezkoder.springjwt.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
id

아이디 (로그인) - username
비번 - password
이메일 - email

닉네임 (한글) - nickname
성별 - gender
	0 : 남성, 1 : 여성, 2 : None
생년월일 - birth


학교 코드 - schoolCode
학년 - s_grade
반 - s_class
번호 - s_number
 */

@Entity
@Table(name = "ih_users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username", "email" }) })
@Data

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user")
	private Long id;
	
	@NotBlank
	private String locaCode;
	
	@NotBlank
	private String schoolCode;
	
	@NotNull
	@Size(max = 20)
	private String username;

	@NotNull
	@Size(max = 50)
	@Email
	private String email;

	@NotNull
	@Size(max = 120)
	private String password;

	@NotBlank
	@Size(max = 1)
	private String s_grade;

	@NotBlank
	@Size(max = 2)
	private String s_class;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ih_user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<PhotoPost> imagePosts = new HashSet<>();

	public User() {
	}

	public User(String locaCode, String code, String username, String email, String password, String grade, String ban) {
		this.locaCode = locaCode;
		this.schoolCode = code;
		this.username = username;
		this.email = email;
		this.password = password;
		this.s_grade = grade;
		this.s_class = ban;
	}
}
