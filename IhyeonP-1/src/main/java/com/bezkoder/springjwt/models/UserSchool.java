package com.bezkoder.springjwt.models;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Table(name = "ih_user_school", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
//@Getter
//@Setter
public class UserSchool {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 5)
	private String locationCode;
	
	@NotBlank
	@Size(max = 50)
	private String schoolName;
	
	@NotBlank
	@Size(max = 10)
	private String schoolCode;
	
	@NotBlank
	@Size(max = 2)
	private String grade;

	@NotBlank
	@Size(max = 3)
	@Column(name = "class")
	private String ban;
	
	@JsonIgnore
	@OneToOne
	@JoinTable(name = "ih_users", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "email"))
	private Set<User> emails = new HashSet<>();
	
	
}
