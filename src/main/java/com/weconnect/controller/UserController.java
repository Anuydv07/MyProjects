package com.weconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.weconnect.exception.UserException;
import com.weconnect.models.User;
import com.weconnect.repositories.UserRepository;
import com.weconnect.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		List<User> users;
		users=userRepo.findAll();
		return users;
	}

	@GetMapping("/api/user/{userId}")
	public User getUserById(@PathVariable("userId")Integer id) throws UserException {		
		User user=userService.findUserById(id);
		return user;
	}
	 
	
	
	@PutMapping("/api/users")
	public User updateUser(@RequestHeader("Authorization") String jwt,
			@RequestBody User user) throws UserException {
      
		User reqUser=userService.findUserByJwt(jwt);
		User updatedUser=userService.updateUser(user,reqUser.getId());
    		  return updatedUser;
	}
		
		
//	@DeleteMapping("/users/{userId}")
//	public String deleteUser(@PathVariable("userId") Integer userId)throws Exception {
//		Optional<User> user=userRepo.findById(userId);
//		
//	       if(user.isEmpty()) {
//	    	   throw new Exception("user not exists with id"+userId);
//	       }
//		userRepo.delete(user.get());
//		return "user deleted successfully with "+userId;
//	}
	
	@PutMapping("/api/userfollow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization") String jwt,
			@PathVariable Integer userId2) throws UserException {
		
		User reqUser=userService.findUserByJwt(jwt) ;
		User user=userService.followUser(reqUser.getId(),userId2);
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User>searchUser(@RequestParam("query")String query){
		List<User> users=userService.searchUser(query);
		return users;
	}
	
	
	@GetMapping("/api/userByMail/{mail}")
	public User getBymailHandler(@PathVariable("mail") String mail) {
		return userService.findUserByEmail(mail);
	}
	
//	@GetMapping("/user/{userId}")
//	public User getUserById(@PathVariable("userId")Integer id) throws Exception {		
//		User user=userService.findUserById(id);
//		return user;
//	}
	
	@GetMapping("api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
		
		
		System.out.print("jwt------"+jwt);
		User user=userService.findUserByJwt(jwt);
		
		user.setPassword(null);
		
		return user;
	}
}
	


