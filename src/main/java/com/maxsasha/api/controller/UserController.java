package com.maxsasha.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.maxsasha.api.dto.UserDto;
import com.maxsasha.api.transformer.UserTransformer;
import com.maxsasha.entity.User;
import com.maxsasha.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
	private final UserService service;

	@GetMapping
	public List<String> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		log.info("Received request to get users. Page size: {}, current Page: {}", size, page);
		Page<User> pageUsers = service.getUsers(PageRequest.of(page, size));
		List<String> pageInfo = new ArrayList<>();
		pageInfo.add(String.format("users: %s", UserTransformer.transform(pageUsers.getContent())));
		pageInfo.add(String.format("Current page: %s", pageUsers.getNumber()));
		pageInfo.add(String.format("Items count: %s", pageUsers.getTotalElements()));
		pageInfo.add(String.format("Pages сount: %s", pageUsers.getTotalPages()));
		return pageInfo;
	}

	@PostMapping
	public UserDto create(@RequestBody UserDto userDto) {
		log.info("Received request to create user with info: name: {}, email: {}", userDto.getName(),
				userDto.getEmail());
		return UserTransformer.transform(service.create(UserTransformer.transform(userDto)));
	}

	@PutMapping("/{id}")
	public UserDto put(@PathVariable String id, @RequestBody UserDto userDto) {
		log.info("Received request to update user with info id: {}, name: {}, email: {} ", id, userDto.getName(),
				userDto.getEmail());
		return UserTransformer.transform(service.edit(UserTransformer.transform(userDto, id)));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		log.info("Received request to delete user id: {}", id);
		service.delete(id);
	}
}