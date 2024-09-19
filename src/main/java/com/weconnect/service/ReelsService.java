package com.weconnect.service;

import java.util.List;

import com.weconnect.models.Reels;
import com.weconnect.models.User;

public interface ReelsService {

	public Reels createReel(Reels reel ,User user);
	public List<Reels> findAllReels();
	public List<Reels> findUsersReel(Integer userId)throws Exception;
	
}
