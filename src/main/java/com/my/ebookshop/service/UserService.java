package com.my.ebookshop.service;

import com.my.ebookshop.dto.UserDto;
import com.my.ebookshop.repository.entity.User;
import org.springframework.security.core.Authentication;

public interface UserService extends Service<UserDto, User> {
  String register(UserDto userDto);

  boolean isRegistrationSuccessful(String registerMessage);

  User getByEmailOrUsername(String emailOrUsername);

  UserDto getDtoByEmailOrUsername(String usernameOrEmail);

  UserDto getAuthenticatedDto(Authentication authentication);

  String getAuthenticatedToken(Authentication authentication);
}
