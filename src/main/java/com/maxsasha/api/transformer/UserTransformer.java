package com.maxsasha.api.transformer;

import java.util.List;
import java.util.stream.Collectors;

import com.maxsasha.api.dto.UserDto;
import com.maxsasha.entity.User;

public class UserTransformer {
	
	public static UserDto transform(User user, String id) {
		return UserDto.builder()
		        .id(id)
		        .name(user.getName())
		        .email(user.getEmail())
		        .build();
	}

	public static User transform(UserDto dto, String id) {
		return User.builder()
		        .id(id)
		        .name(dto.getName())
		        .email(dto.getEmail())
		        .build();
	}
	public static List<UserDto> transform(List<User> users) {
		return users.stream()
		        .map(u -> transform(u,u.getId()))
		        .collect(Collectors.toList());
	}
}