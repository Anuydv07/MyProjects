package com.weconnect.service;

import java.util.List;

import com.weconnect.models.Chat;
import com.weconnect.models.Message;
import com.weconnect.models.User;

public interface MessageService {

	public Message createMessage(User user,Integer chatId,Message req)throws Exception;
	public List<Message> findChatsMessages(Integer chatId)throws Exception;
}
