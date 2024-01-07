package com.bezkoder.springjwt.models;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
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
@Table(name = "ih_images_heart", uniqueConstraints = { @UniqueConstraint(columnNames = { "post_id", "user" }) })
@Getter
@Setter
public class PhotoHeart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "user")
	private User user;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "post_id")
	private PhotoPost photoPost;

	@CreationTimestamp
	@Column(name="uploaded_on", updatable = false)
	private LocalDate uploadedOn;

	public PhotoHeart() {
	}

	public PhotoHeart(User user, PhotoPost photoPost) {
		this.user = user;
		this.photoPost = photoPost;
	}
}
