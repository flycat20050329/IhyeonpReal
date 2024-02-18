package com.bezkoder.springjwt.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
	public List<Photo> findAllByPhotoPostId(Long postId);

	public List<Photo> findAllByPhotoPostUploadedOnBetween(LocalDateTime start, LocalDateTime end);

}
