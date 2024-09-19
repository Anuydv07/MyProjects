package com.weconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weconnect.models.Reels;
import com.weconnect.models.User;
import com.weconnect.repositories.ReelsRepository;

@Service
public class ReelsServiceImplementation implements ReelsService{

	@Autowired 
	private ReelsRepository reelsRepo;
	
	@Autowired
	private UserService userService;
	@Override
	public Reels createReel(Reels reel, User user)  {
		Reels createReel=new Reels();
		
		createReel.setTitle(reel.getTitle());
		createReel.setUser(user);
		createReel.setVideo(reel.getVideo());
		
		return reelsRepo.save(createReel);
	}

	@Override
	public List<Reels> findAllReels() {
		return reelsRepo.findAll();
	}

	@Override
	public List<Reels> findUsersReel(Integer userId) throws Exception {
		userService.findUserById(userId);
		return reelsRepo.findByUserId(userId);
	}

}
