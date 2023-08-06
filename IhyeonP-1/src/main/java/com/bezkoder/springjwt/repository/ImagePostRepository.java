package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.ImagePost;

@Repository
public interface ImagePostRepository extends JpaRepository<ImagePost, Long>{
	
}
