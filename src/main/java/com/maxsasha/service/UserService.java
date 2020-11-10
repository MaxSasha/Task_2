package com.maxsasha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.maxsasha.db.repository.UserRepository;
import com.maxsasha.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository dbActions;

	public List<User> getUsers() {
		return dbActions.findAll();
	}

	public Optional<User> getUser(String id) {
		return dbActions.findById(id);
	}

	public void create(User user) {
		dbActions.save(user);
	}

	public User edit(User user) {
		return dbActions.save(user);
	}

	public void delete(String id) {
		dbActions.deleteById(id);
	}
}