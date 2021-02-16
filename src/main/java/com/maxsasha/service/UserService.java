package com.maxsasha.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.maxsasha.db.repository.UserRepository;
import com.maxsasha.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;

	public Page<User> getUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Optional<User> getUser(String id) {
		return userRepository.findById(id);
	}

	public User create(User user) {
		return userRepository.save(user);
	}

	public User edit(User user) {
		return userRepository.save(user);
	}

	public void delete(String id) {
		userRepository.deleteById(id);
	}
}