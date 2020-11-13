package com.maxsasha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.maxsasha.db.repository.UserRepository;
import com.maxsasha.entity.User;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public Optional<User> getUser(String id) {
		return userRepository.findById(id);
	}

	public void create(User user) {
		userRepository.save(user);
	}

	public User edit(User user) {
		return userRepository.save(user);
	}

	public void delete(String id) {
		userRepository.deleteById(id);
	}
}