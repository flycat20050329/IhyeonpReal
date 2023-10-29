package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
	public List<Image> findByImagePostId(Long postId);
	
}
