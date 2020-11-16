package com.maxsasha.api.transformer;

import java.util.List;
import java.util.stream.Collectors;

import com.maxsasha.api.dto.UserDto;
import com.maxsasha.entity.User;

public class UserTransformer {

	public static User transform(UserDto dto) {
		return new User(dto.getId(),dto.getName(),dto.getEmail());
	}

	public static UserDto transform(User user) {
		return new UserDto(user.getId(),user.getName(),user.getEmail());
	}

	public static List<UserDto> transform(List<User> users) {
		return users.stream().map(m -> transform(m)).collect(Collectors.toList());
	}
}