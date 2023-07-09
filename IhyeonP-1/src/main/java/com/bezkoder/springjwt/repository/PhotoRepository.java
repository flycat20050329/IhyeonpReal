package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>{
	
	
}
