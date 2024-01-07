package com.bezkoder.springjwt.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Photo;
import com.bezkoder.springjwt.models.PhotoPost;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
	public List<Photo> findByPhotoPostId(Long postId);

	public void deleteAllByPhotoPost(PhotoPost photoPost);
    List<Photo> findAllByPhotoPostUploadedOnBetween(LocalDate start, LocalDate end);

}
