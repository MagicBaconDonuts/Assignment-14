package com.assignment14.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment14.domain.Message;
import com.assignment14.domain.MessageDTO;
import com.assignment14.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepo;

	@Autowired
	private UserService userService;
	public List<Message> findAll() {
		return messageRepo.findAll();
	}

	public Message save(Message message) {
		return messageRepo.save(message);
	}

	public List<Message> findAllByChannelId(Long channelId) {
		return messageRepo.findByChannel_id(channelId);
	}

	public List<MessageDTO> findAllByChannelIdDTO(Long channelId) {
		List<Message> messages = messageRepo.findAllByChannelId(channelId);
		List<MessageDTO> newMessages = new ArrayList<MessageDTO>();
		for(Message mess:messages) {
			MessageDTO newMess = new MessageDTO();
			newMess.setChannelId(mess.getChannel().getId());
			newMess.setMessageName(mess.getUser().getUsername());
			newMess.setMessageContent(mess.getMessage());
			newMess.setUserId(mess.getUser().getId());
			newMessages.add(newMess);
		}
		return newMessages;
	}
}
