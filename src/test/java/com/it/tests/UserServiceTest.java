package com.it.tests;

import java.util.Collection;
import java.util.Optional;

import javax.inject.Inject;

import static org.junit.Assert.*;
import org.junit.Test;

import com.it.domain.User;
import com.it.service.UserService;

public class UserServiceTest extends BaseTest {
	
	
	@Inject
	private UserService userService;
	
	
	@Test
	public void userCrudTest() {
		
    	User user = new User();
    	user.setUsername("test");
    	user.setPassword("123456");
    	user.setFirstName("test");
    	user.setLastName("test");
    	user.setEmail("test@user.com");
    	userService.addNewUser(user);
    	
    	Optional<User> dbUser = userService.findByUsername("test");
    	
    	assertNotNull("User not found", dbUser.get());
    	
    	Collection<User> searchResults = userService.searchUsers("test@user.com");
    	
    	assertEquals(1, searchResults.size());
    	
    	User resultUser = searchResults.iterator().next();
    	
    	assertEquals("test@user.com", resultUser.getEmail());
	}

}
