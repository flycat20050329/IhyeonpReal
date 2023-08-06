package com.bezkoder.springjwt.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ih_images", uniqueConstraints = { @UniqueConstraint(columnNames = "post_id"),
		@UniqueConstraint(columnNames = "number") })

@Getter
@Setter
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@NotNull
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[] image;

	@NotNull
	@Column(name = "number")
	private int index;

	@NotNull
	@ManyToOne()
	@JoinColumn(name = "post_id")
	private ImagePost imagePost;

	public Image() {
	}

	public Image(byte[] image, ImagePost imagePost, int index) {
		this.image = image;
		this.imagePost = imagePost;
		this.index = index;
	}
}
