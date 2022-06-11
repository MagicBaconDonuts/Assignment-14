package com.assignment14.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment14.domain.Message;
import com.assignment14.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepo;

	public List<Message> findAll() {
		return messageRepo.findAll();
	}

	public Message save(Message message) {
		return messageRepo.save(message);
	}

	public List<Message> findAllByChannelId(Long channelId) {
		return messageRepo.findByChannel_id(channelId);
	}

}
