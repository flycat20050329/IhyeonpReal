package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.ClubPost;

@Repository
public interface ClubPostRepository extends JpaRepository<ClubPost, Long>{
	public List<ClubPost> findAllById(Long id);
}
