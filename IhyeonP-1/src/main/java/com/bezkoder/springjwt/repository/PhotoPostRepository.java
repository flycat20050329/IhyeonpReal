package com.bezkoder.springjwt.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.PhotoPost;

@Repository
public interface PhotoPostRepository extends JpaRepository<PhotoPost, Long> {
	public List<PhotoPost> findAllById(Long id);

}
