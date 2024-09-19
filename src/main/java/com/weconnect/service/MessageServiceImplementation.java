package com.weconnect.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weconnect.models.Chat;
import com.weconnect.models.Message;
import com.weconnect.models.User;
import com.weconnect.repositories.ChatRepository;
import com.weconnect.repositories.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService{

	@Autowired
	private MessageRepository messageRepo;
	
	@Autowired
	private ChatService chatService;
	@Autowired
	private ChatRepository chatRepo;
	
	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws Exception {
		
		Message  message=new Message();
		Chat chat=chatService.findChatById(chatId);
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setTimeStamp(LocalDateTime.now());
		Message savedMsg= messageRepo.save(message);
		chat.getMessages().add(savedMsg);
		chatRepo.save(chat);
		return savedMsg;
	}

	@Override
	public List<Message> findChatsMessages(Integer chatId) throws Exception {
	
		Chat chat=chatService.findChatById(chatId);
		
		return messageRepo.findByChatId(chatId);
	}

}
