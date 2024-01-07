package com.bezkoder.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.PhotoReply;

@Repository
public interface PhotoReplyRepository extends JpaRepository<PhotoReply, Long> {
	public List<PhotoReply> findAllById(Long id);
	public List<PhotoReply> findAllByPhotoPostId(Long postId);
}
