package com.my.ebookshop.service;

import com.my.ebookshop.dto.UserDto;
import com.my.ebookshop.repository.CartRepository;
import com.my.ebookshop.repository.ProfileRepository;
import com.my.ebookshop.repository.UserRepository;
import com.my.ebookshop.repository.entity.*;
import com.my.ebookshop.repository.entity.util.UserRole;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class UserServiceImpl
  extends ServiceImpl<UserRepository, UserDto, User, UUID>
  implements UserService {
  //
  public static final String REGISTER_SUCCESSFUL =
    "Registration successfully started :) ! Please verify your email to finish the process.";
  public static final String REGISTER_FAILED = "Something went wrong (: !";
  @Autowired
  private ProfileRepository profileRepository;
  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public String register(UserDto dto) {
    String uniqueEmail = dto.getEmail();
    if (getRepository().findByEmail(uniqueEmail).isPresent()) {
      return REGISTER_FAILED;
    }
    try {
      User user = new User();
      user.setUsername(dto.getUsername());
      user.setEmail(uniqueEmail);
      String plainText = dto.getPassword();
      user.setPassword(passwordEncoder.encode(plainText));
      user.setToken("Basic "
        + Base64.encodeBase64String((uniqueEmail + ":" + plainText).getBytes(StandardCharsets.US_ASCII))
      );
      user.setRole(UserRole.valueOf(dto.getRoleKey()));
      //
      Profile profile = new Profile();
      user.setProfile(profile);
      profile.setFirstName(dto.getFirstName());
      profile.setLastName(dto.getLastName());
      try {
        profile.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
      }
      catch (Exception exception) {
        // do nothing: leave profile.dateOfBirth null
      }
      profile.setAddress(dto.getAddress());
      profile.setContact(dto.getContact());
      //
      profileRepository.save(profile);
      //
      Cart cart = new Cart();
      user.setCart(cart);
      //
      cartRepository.save(cart);
      //
      dto.setId(String.valueOf(getRepository().save(user).getId()));
    }
    catch (Exception exception) {
      return REGISTER_FAILED;
    }
    return REGISTER_SUCCESSFUL;
  }

  @Override
  public boolean isRegistrationSuccessful(String registrationResult) {
    return REGISTER_SUCCESSFUL.equals(registrationResult);
  }

  @Override
  public User getByEmailOrUsername(String emailOrUsername) {
    return getRepository()
      .findByEmail(emailOrUsername)
      .orElseGet(() -> getRepository().findByUsername(emailOrUsername)
        .orElseThrow(() -> {
          String message = "No user matches the credentials !";
          return new UsernameNotFoundException(message);
        }));
  }

  @Override
  public UserDto getDtoByEmailOrUsername(String emailOrUsername) {
    return toDto(getByEmailOrUsername(emailOrUsername));
  }

  @Override
  public UserDto getAuthenticatedDto(Authentication authentication) {
    return getDtoByEmailOrUsername(authentication.getName());
  }

  @Override
  public String getAuthenticatedToken(Authentication authentication) {
    return getByEmailOrUsername(authentication.getName()).getToken();
  }

  @Override
  public UserDto toDto(User user) {
    UserDto result = new UserDto();
    result.setId(String.valueOf(user.getId()));
    result.setUsername(user.getUsername());
    result.setEmail(user.getEmail());
    result.setPassword(user.getPassword());
    result.setRoleKey(user.getRole().name());
    result.setFirstName(user.getProfile().getFirstName());
    result.setLastName(user.getProfile().getLastName());
    try {
      result.setDateOfBirth(user.getProfile().getDateOfBirth().toString());
    }
    catch (Exception exception) {
      result.setDateOfBirth("");
    }
    result.setAddress(user.getProfile().getAddress());
    result.setContact(user.getProfile().getContact());
    return result;
  }

  @Override
  public User fromDto(UserDto dto) {
    return null;
  }
}
