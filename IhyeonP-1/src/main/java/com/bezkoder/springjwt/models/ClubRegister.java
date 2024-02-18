package com.bezkoder.springjwt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ih_clubRegister", uniqueConstraints = { @UniqueConstraint(columnNames = { "club", "user" }) })
@Getter
@Setter
public class ClubRegister {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "club", referencedColumnName = "id")
	private ClubPost clubPost;
	
	@ManyToOne
	@JoinColumn(name = "user", referencedColumnName = "user")
	private User user;
	
	public ClubRegister() {
		
	}
	
	public ClubRegister(ClubPost clubPost, User user) {
		this.clubPost = clubPost;
		this.user = user;
	}
}
