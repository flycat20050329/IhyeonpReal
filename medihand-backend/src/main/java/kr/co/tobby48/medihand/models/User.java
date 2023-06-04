package kr.co.tobby48.medihand.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users", 
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username")
})
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@NotNull
	@Size(max = 50)
	private String username;

	@NotBlank
	@NotNull
	@Size(max = 120)
	private String password;

	@Size(max = 50)
	private String name;

	@Size(max = 255)
	private String email;

	@Size(max = 255)
	private String optime;

	@Size(max = 50)
	private String phone;

	@Size(max = 50)
	private String fax;

	@Size(max = 500)
	private String map;

//	@NotNull
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(  name = "user_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user")    
	private Set<Patient> patients = new HashSet<>();
		
	@OneToMany(mappedBy = "user")    
	private Set<Management> managements = new HashSet<>();
	
	
	
	public User() {
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email=email;
	}
	
}
