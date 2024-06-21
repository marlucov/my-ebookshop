package com.my.ebookshop.service;

import com.my.ebookshop.repository.UserRepository;
import com.my.ebookshop.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(usernameOrEmail)
      .orElseGet(() -> userRepository.findByUsername(usernameOrEmail)
        .orElseThrow(() -> {
          String message = "No user matches the credentials !";
          return new UsernameNotFoundException(message);
        }));
    return org.springframework.security.core.userdetails.User
      .withUsername(user.getEmail())
      .password(user.getPassword())
      .authorities(user.getRole().name())
      .build();
  }
}
