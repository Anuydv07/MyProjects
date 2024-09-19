package com.weconnect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.weconnect.models.Story;
import com.weconnect.models.User;
import com.weconnect.repositories.StoryRepository;
import com.weconnect.service.StoryService;
import com.weconnect.service.UserService;


@CrossOrigin(origins = "*")
@RestController
public class StoryController {

	@Autowired
	private StoryService storyService;
	@Autowired
	private StoryRepository storyRepo;
	@Autowired
	UserService userService;
	
	@PostMapping("/api/story")
	public Story createStory(@RequestBody Story story,@RequestHeader("Authorization") String jwt) {
		User user=userService.findUserByJwt(jwt);
		Story newStory=storyService.createStory(story,user);
		return newStory;
	}
	
	@GetMapping("/api/story/user/{userId}")
	public List<Story> findUserStory(@PathVariable Integer userId, @RequestHeader("Authorization") String jwt) throws Exception {
		User user=userService.findUserByJwt(jwt);
		List<Story> stories=storyService.findStoryByUserId(userId);
		return stories;
	}

}
