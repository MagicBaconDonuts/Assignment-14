package com.assignment14.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment14.domain.User;
import com.assignment14.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public String findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public User save(User user) {
		return userRepo.save(user);
	}
}
