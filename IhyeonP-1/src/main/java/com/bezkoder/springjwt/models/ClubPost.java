package com.bezkoder.springjwt.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ih_clubPost")
@Getter
@Setter
public class ClubPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String text;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "user")
	private User user;
	
	public ClubPost() {
	}

	public ClubPost(User user, String text) {
		this.user = user;
		this.text = text;
	}
}
