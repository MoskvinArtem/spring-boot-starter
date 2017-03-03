package com.it.rest;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.it.domain.User;
import com.it.service.UserService;

/**
 * REST resource for user CRUD operations
 * @author Evgenym
 *
 */

@RestController
@RequestMapping("/rest/api/users")
public class UserResource {
	
	@Inject
	private UserService userService;
	
	
    @RequestMapping(method = RequestMethod.GET)
    public Page<User> getUsers(){
        return userService.getAllUsers(new PageRequest(0, Integer.MAX_VALUE));
    }
    
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long userId){
        return userService.findById(userId);
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    User createUser(User user){
        return userService.addNewUser(user);
    }
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/search")
    Collection<User> findUsers(@RequestParam("q")String searchTerm) {
    	return userService.searchUsers(searchTerm);
    }

}
