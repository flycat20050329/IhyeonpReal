package kr.co.tobby48.medihand.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.tobby48.medihand.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//	List<User> getAll();
	
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);
	
	Boolean existsByEmail(String email);
}
