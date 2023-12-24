package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.PhotoHeart;

@Repository
public interface PhotoHeartRepository extends JpaRepository<PhotoHeart, Long> {
	public List<PhotoHeart> findAllByPhotoPostIdAndUserId(Long postId, Long userId);

	public List<PhotoHeart> findAllByPhotoPostId(Long postId);
}
