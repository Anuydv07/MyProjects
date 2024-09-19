package com.weconnect.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weconnect.models.Story;
import com.weconnect.models.User;
import com.weconnect.repositories.StoryRepository;

@Service
public class StoryServiceImplementation implements StoryService{

	@Autowired
	private StoryRepository storyRepo;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public Story createStory(Story story, User user) {
		Story newStory=new Story();
		newStory.setCaption(story.getCaption());
		newStory.setCaption(story.getCaption());
		newStory.setUser(user);
		newStory.setTimestamp(LocalDateTime.now());
		return storyRepo.save(newStory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId) throws Exception{
		User user=userService.findUserById(userId);
		return storyRepo.findByUserId(userId);
	}

}
