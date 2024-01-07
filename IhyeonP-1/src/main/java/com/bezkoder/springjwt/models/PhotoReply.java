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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ih_images_reply")
@Getter
@Setter
public class PhotoReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "user")
	private User user;

	@Size(max = 250)
	@NotNull
	private String text;

	@CreationTimestamp
	@Column(name = "uploaded_on", updatable=false)
	private LocalDate uploadedOn;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "post_id")
	private PhotoPost photoPost;

	public PhotoReply() {
	}

	public PhotoReply(User user, String text, PhotoPost photoPost) {
		this.user = user;
		this.text = text;
		this.photoPost = photoPost;
	}
}
