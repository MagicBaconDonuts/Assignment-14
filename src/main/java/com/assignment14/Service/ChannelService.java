package com.assignment14.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment14.domain.Channel;
import com.assignment14.domain.User;
import com.assignment14.repository.ChannelRepository;

@Service
public class ChannelService {

	@Autowired
	private ChannelRepository channelRepo;

	public List<Channel> findAll() {
		return channelRepo.findAll();
	}

	public Optional<Channel> findById(Long channelId) {
		return channelRepo.findById(channelId);
	}
}
