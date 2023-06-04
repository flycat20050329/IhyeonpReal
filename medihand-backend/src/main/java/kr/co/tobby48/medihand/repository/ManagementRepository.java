package kr.co.tobby48.medihand.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.tobby48.medihand.models.Management;

@Repository
public interface ManagementRepository extends JpaRepository<Management, Long> {
	 List<Management> findByUser_Id(@Param(value = "user_id") Long user_id);
}
