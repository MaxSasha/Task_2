package com.maxsasha.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxsasha.api.dto.UserDto;
import com.maxsasha.api.transformer.UserTransformer;
import com.maxsasha.entity.User;
import com.maxsasha.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
	private final UserService service;
	
	@GetMapping("/users")
	public List<UserDto> getUsers() {
			log.info("Received request to get users ");
			List<UserDto> users = UserTransformer.transform(service.getUsers());
			log.info("Request to get users successfully completed. Users count:{}",users.size());
			return users;
	}

	@PostMapping("/users")
	public void create(@RequestBody UserDto userDto) {
			log.info("Received request to create user");
			service.create(UserTransformer.transform(userDto));
			log.info("Request to create successfuly completed with info: name:{} , email:{}", userDto.getName(),userDto.getEmail());		
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<Object> put(@PathVariable String id, @RequestBody UserDto userDto) {
			log.info("Received request to update user id:{0}",id);
			Optional<User> user = service.getUser(id);
			if (user.isPresent()) {
				UserDto updatedUser = UserTransformer.transform(
						service.edit(User.builder().id(id).name(userDto.getName()).email(userDto.getEmail()).build()));
				log.info("Request to update successfuly completed with info: id:{},  name:{}, email:{}", id,userDto.getName(),userDto.getEmail());
				return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
				
			} else {
				log.info("Request to update successfuly completed with info: user not found");
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
	}

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable("id") String id) {
			log.info("Received request to delete user id:{}",id);
			service.delete(id);
			log.info("Request to delete successfuly completed");		
	}
}