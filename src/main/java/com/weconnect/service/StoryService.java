package com.weconnect.service;

import java.util.List;

import com.weconnect.models.Story;
import com.weconnect.models.User;

public interface StoryService {

	public Story createStory(Story story,User user);
	public List<Story> findStoryByUserId(Integer userId)throws Exception;
}
