package com.bezkoder.springjwt.models;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ih_images_post")
@Getter
@Setter
public class ImagePost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "user")
	private User user;

	@NotNull
//	@Size(max = 11)
	private int heart;

//	@Size(max = 250)
	private String text;

//	@NotNull
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private java.util.Date uploaded_on;

	@JsonIgnore
	@OneToMany(mappedBy = "imagePost")
	private Set<Image> Images = new HashSet<>();

	public ImagePost() {
	}
	
	public ImagePost(Long id) {
		this.id = id;
	}
	
	public ImagePost(User user, int heart, String text) {
		this.user = user;
		this.heart = heart;
		this.text = text;
	}
}