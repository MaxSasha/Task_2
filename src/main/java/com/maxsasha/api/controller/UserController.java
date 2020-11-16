package com.maxsasha.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Map<String, Object>> getUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		log.info("Received request to get users ");
		Page<User> pageUsers = service.getUsers(PageRequest.of(page, size));
		Map<String, Object> response = new HashMap<>(); 
		response.put("users", UserTransformer.transform(pageUsers.getContent()));
		response.put("Current page", pageUsers.getNumber());
		response.put("Items count", pageUsers.getTotalElements());
		response.put("Pages сount", pageUsers.getTotalPages());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	public void create(@RequestBody UserDto userDto) {
		log.info("Received request to create user with info: name: {}, email: {}", userDto.getName(),
				userDto.getEmail());
		service.create(UserTransformer.transform(userDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> put(@PathVariable String id, @RequestBody UserDto userDto) {
		log.info("Received request to update user with info id: {}, name: {},email: {} ", id, userDto.getName(),
				userDto.getEmail());
		userDto.setId(id);
		UserDto updatedUser = UserTransformer.transform(service.edit(UserTransformer.transform(userDto)));
		return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) {
		log.info("Received request to delete user id:{}", id);
		service.delete(id);
	}
}