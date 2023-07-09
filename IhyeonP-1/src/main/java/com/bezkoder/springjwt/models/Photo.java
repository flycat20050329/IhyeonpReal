package com.bezkoder.springjwt.models;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ih_images")
@Getter
@Setter

public class Photo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private Blob image;

	@NotBlank
	@Size(max = 20)
	private Long user;

	@NotBlank
	@Size(max = 11)
	private int like;

	@Size(max = 250)
	private String text;

	public Photo() {
	}

	public Photo(Blob image, Long user, int like, String text) {
		this.image = image;
		this.user = user;
		this.like = like;
		this.text = text;
	}
}
