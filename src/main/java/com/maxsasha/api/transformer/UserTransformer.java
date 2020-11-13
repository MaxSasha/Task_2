package com.maxsasha.api.transformer;

import java.util.List;
import java.util.stream.Collectors;

import com.maxsasha.api.dto.UserDto;
import com.maxsasha.entity.User;

public class UserTransformer {

	public static User transform(UserDto dto) {
		return User.builder()
				.id(dto.getId())
				.name(dto.getName())
				.email(dto.getEmail())
				.build();
	}
	
	public static UserDto transform(User user) {
		return UserDto.builder()
				.id(user.getId())
				.name(user.getName())
				.email(user.getEmail())
				.build();
	}
	public static List<UserDto> transform(List<User> users)
	{
		return users.stream().map(m -> transform(m)).collect(Collectors.toList());
	}
}