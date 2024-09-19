package com.weconnect.service;

import java.util.List;

import com.weconnect.models.Chat;
import com.weconnect.models.User;

public interface ChatService {

	public Chat createChat(User reqUser,User user2);
	public Chat findChatById(Integer chatId) throws Exception;
	public List<Chat> findUsersChat(Integer userId);
}
