package kr.co.tobby48.medihand.security.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.tobby48.medihand.models.User;
import kr.co.tobby48.medihand.payload.request.profile.ProfileRequest;
import kr.co.tobby48.medihand.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@Transactional
	public User findUserByUserName(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return user;
	}
	
	@Transactional
	public User updateUser(ProfileRequest profileRequest) throws UsernameNotFoundException {
		User user = userRepository.findById(profileRequest.getId()).orElseGet(User::new);
		user.setEmail(profileRequest.getEmail());
		user.setFax(profileRequest.getFax());
		user.setMap(profileRequest.getMap());
		user.setName(profileRequest.getName());
		user.setOptime(profileRequest.getOptime());
		user.setPhone(profileRequest.getPhone());
		userRepository.save(user);

		return user;
	}
	
//	@Transactional
//	public List<User> getAll() {
//		List<User> users = userRepository.getAll();
//		return users;
//	}
}
