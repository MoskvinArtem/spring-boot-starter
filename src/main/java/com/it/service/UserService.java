package com.it.service;


import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.it.domain.User;
import com.it.repository.UserRepository;
/**
 * 
 * Reference implementation for user management
 *
 */
@Service
public class UserService implements UserDetailsService{
	
	@Inject
	private UserRepository userRepository;
	
	@Inject
	private Md5PasswordEncoder encoder;
	
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	/**
	 * 
	 * @return Returns currently authenticated {@link com.it.domain.User}
	 */
	public User currentUser() {
		return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
	}
	
	/**
	 *  Add new user to DB
	 * @param user user object to be added
	 * @return Newly created user
	 * 
	 */
	public User addNewUser(User user) {
		user.setPassword(encoder.encodePassword(user.getPassword(), null));
		return userRepository.save(user);
	}

	/**
	 * Updates existing user
	 * @param user user to be updated
	 */
	public void updateUser(User user) {
		userRepository.save(user);
	}

	
	/**
	 * Get all users from DB
	 * @param pr PageRequest
	 * @return page of users from DB
	 */
	public Page<User> getAllUsers(Pageable pr) {
		return userRepository.findAll(pr);
	}

	/**
	 * Search users by mail or username
	 * @param searchTerm mail or username of user
	 * @return found DB users
	 */
	public Collection<User> searchUsers(String searchTerm) {
		return userRepository.searchUser("%" + searchTerm + "%");
	}

	
	/**
	 * Get user by ID
	 * @param userId userID
	 * @return user from DB
	 */
	public User findById(Long userId) {
		return userRepository.findOne(userId);
	}


	
}
