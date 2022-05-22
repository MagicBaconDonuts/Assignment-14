package com.assignment14.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment14.domain.User;
import com.assignment14.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepo;

	public Optional<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public User save(User user) {
		return userRepo.save(user);
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

}
