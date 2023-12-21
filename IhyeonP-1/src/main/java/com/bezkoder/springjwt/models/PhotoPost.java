package com.bezkoder.springjwt.models;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ih_images_post")
@Getter
@Setter
public class PhotoPost {

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

	@Size(max = 100)
	private String text;

	@CreationTimestamp
	private java.util.Date uploaded_on;

	@JsonIgnore
	@OneToMany(mappedBy = "photoPost")
	private Set<Photo> Images = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "photoPost")
	private Set<PhotoReply> Replys = new HashSet<>();

	public PhotoPost() {
	}

	public PhotoPost(User user, int heart, String text) {
		this.user = user;
		this.heart = heart;
		this.text = text;
	}
}
