package com.weconnect.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weconnect.config.JwtProvider;
import com.weconnect.exception.UserException;
import com.weconnect.models.User;
import com.weconnect.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	UserRepository userRepo;

	@Override
	public User registerUser(User user) {
		User newUser =new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword() );
		User savedUser=userRepo.save(newUser);
		return savedUser;
		
	}

	@Override
	public User findUserById(Integer userId) throws UserException {
		Optional<User> user=userRepo.findById(userId);
		if(user.isPresent())
			return user.get();
		throw new UserException("user not exists with userid"+ userId);
	}

	@Override
	public User findUserByEmail(String email) {
		User user =userRepo.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws UserException {
		User reqUser=findUserById(reqUserId); 
		User user2=findUserById(userId2); 
		reqUser.getFollowings().add(user2.getId());
		user2.getFollowers().add(reqUser.getId());
	    userRepo.save(reqUser);
	    userRepo.save(user2);
	    return reqUser;
	}

	@Override
	public User updateUser(User user,Integer userId) throws UserException {
		
		
		Optional<User> user1=userRepo.findById(userId);
		if(user1.isEmpty())
			throw new UserException("user not exist with id"+userId);
		User oldUser=user1.get();
		 
	       if (user.getEmail()!=null)
	       oldUser.setEmail(user.getEmail());
	       if(user.getFirstName()!=null)
		   oldUser.setFirstName(user.getFirstName());
	       if(user.getLastName()!=null)
		   oldUser.setLastName(user.getLastName());
//	       if(user.getPassword()!=null)
//		   oldUser.setPassword(user.getPassword());
	       if(user.getGender()!=null)
	    	   oldUser.setGender(user.getGender());
		    
		   User newUser=userRepo.save(oldUser);
		   return newUser;
	
	}

	@Override
	public List<User> searchUser(String query) {
		return userRepo.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email=JwtProvider.getEmailFromJwtToken(jwt); 
		User user=userRepo.findByEmail(email);
		return user;
	}

}
