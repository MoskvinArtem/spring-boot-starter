package com.it.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.it.domain.User;


public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM usermaster u where u.username like :searchTerm or u.email like :searchTerm")
	List<User> searchUser(@Param("searchTerm") String searchTerm);
}
