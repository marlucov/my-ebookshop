package com.my.ebookshop.service;

import com.my.ebookshop.dto.UserDto;
import com.my.ebookshop.repository.CartRepository;
import com.my.ebookshop.repository.ProfileRepository;
import com.my.ebookshop.repository.UserRepository;
import com.my.ebookshop.repository.entity.Cart;
import com.my.ebookshop.repository.entity.Profile;
import com.my.ebookshop.repository.entity.User;
import com.my.ebookshop.repository.entity.util.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
  @Spy
  @InjectMocks
  private UserServiceImpl userService;
  @Mock
  private UserRepository userRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private ProfileRepository profileRepository;
  @Mock
  private CartRepository cartRepository;

  private void setUpUserServiceImplRepository() {
    doReturn(userRepository).when(userService).getRepository();
  }

  @Test
  public void getDtoByEmailOrUsername_whenUserExists_thenReturnUserDto_test() {
    // given
    User user = new User();
    user.setId(UUID.randomUUID());
    user.setUsername("username");
    user.setEmail("email");
    //
    UserDto assertedDto = new UserDto();
    assertedDto.setId(String.valueOf(user.getId()));
    assertedDto.setUsername(user.getUsername());
    assertedDto.setEmail(user.getEmail());
    //
    doReturn(user).when(userService).getByEmailOrUsername(user.getEmail());
    doReturn(assertedDto).when(userService).toDto(user);
    // when
    UserDto dto = userService.getDtoByEmailOrUsername(user.getEmail());
    // then
    assertEquals(assertedDto.getId(), dto.getId());
    assertEquals(assertedDto.getEmail(), dto.getEmail());
    assertEquals(assertedDto.getUsername(), dto.getUsername());
    verify(userService).getByEmailOrUsername(user.getEmail());
    verify(userService).toDto(user);
  }

  @Test
  public void register_whenDtoOK_thenReturnRegisterSuccessful_test() {
    // given
    UserDto dto = new UserDto();
    dto.setEmail("buyer@buyer.ro");
    dto.setPassword("password");
    dto.setRoleKey(UserRole.ROLE_BUYER.name());
    dto.setFirstName("John");
    dto.setLastName("Doe");
    dto.setDateOfBirth("2008-08-08");
    dto.setAddress("123 Main St");
    dto.setContact("1234567890");
    //
    UUID profileId = UUID.randomUUID();
    UUID cartId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
    //
    setUpUserServiceImplRepository();
    //
    when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
    when(passwordEncoder.encode(dto.getPassword()))
      .thenReturn(new BCryptPasswordEncoder().encode(dto.getPassword()));
    when(profileRepository.save(any(Profile.class))).thenAnswer(invocation -> {
      Profile result = invocation.getArgument(0);
      result.setId(profileId);
      return result;
    });
    when(cartRepository.save(any(Cart.class))).thenAnswer(invocation -> {
      Cart result = invocation.getArgument(0);
      result.setId(cartId);
      return result;
    });
    when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
      User result = invocation.getArgument(0);
      result.setId(userId);
      return result;
    });
    // when
    String registrationResult = userService.register(dto);
    // then
    assertEquals(UserServiceImpl.REGISTER_SUCCESSFUL, registrationResult);
    verify(userRepository).save(userCaptor.capture());
    User capturedUser = userCaptor.getValue();
    assertEquals(profileId, capturedUser.getProfile().getId());
    assertEquals(cartId, capturedUser.getCart().getId());
    assertEquals(userId, capturedUser.getId());
    verify(userRepository).findByEmail(dto.getEmail());
    verify(profileRepository).save(any(Profile.class));
    verify(cartRepository).save(any(Cart.class));
  }

  @Test
  public void register_whenDtoDateOfBirthInvalid_thenReturnRegisterSuccessful_test() {
    // given
    UserDto dto = new UserDto();
    dto.setEmail("buyer@buyer.ro");
    dto.setPassword("password");
    dto.setRoleKey(UserRole.ROLE_BUYER.name());
    dto.setFirstName("John");
    dto.setLastName("Doe");
    dto.setDateOfBirth("08-08-2008");
    dto.setAddress("123 Main St");
    dto.setContact("1234567890");
    //
    UUID profileId = UUID.randomUUID();
    UUID cartId = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
    //
    setUpUserServiceImplRepository();
    //
    when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
    when(passwordEncoder.encode(dto.getPassword()))
      .thenReturn(new BCryptPasswordEncoder().encode(dto.getPassword()));
    when(profileRepository.save(any(Profile.class))).thenAnswer(invocation -> {
      Profile result = invocation.getArgument(0);
      result.setId(profileId);
      return result;
    });
    when(cartRepository.save(any(Cart.class))).thenAnswer(invocation -> {
      Cart result = invocation.getArgument(0);
      result.setId(cartId);
      return result;
    });
    when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
      User result = invocation.getArgument(0);
      result.setId(userId);
      return result;
    });
    // when
    String registrationResult = userService.register(dto);
    // then
    assertEquals(UserServiceImpl.REGISTER_SUCCESSFUL, registrationResult);
    verify(userRepository).save(userCaptor.capture());
    User capturedUser = userCaptor.getValue();
    assertEquals(profileId, capturedUser.getProfile().getId());
    assertEquals(cartId, capturedUser.getCart().getId());
    assertEquals(userId, capturedUser.getId());
    verify(userRepository).findByEmail(dto.getEmail());
    verify(profileRepository).save(any(Profile.class));
    verify(cartRepository).save(any(Cart.class));
    assertNull(capturedUser.getProfile().getDateOfBirth());
  }

  @Test
  public void register_whenUserExists_thenReturnRegisterFailed_test() {
    // given
    UserDto dto = new UserDto();
    dto.setEmail("buyer@buyer.ro");
    dto.setPassword("password");
    dto.setRoleKey(UserRole.ROLE_BUYER.name());
    dto.setFirstName("John");
    dto.setLastName("Doe");
    dto.setDateOfBirth("2008-08-08");
    dto.setAddress("123 Main St");
    dto.setContact("1234567890");
    //
    User user = new User();
    user.setId(UUID.randomUUID());
    user.setUsername("username");
    user.setEmail("buyer@buyer.ro");
    //
    setUpUserServiceImplRepository();
    //
    when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(user));
    // when
    String registrationResult = userService.register(dto);
    // then
    assertEquals(UserServiceImpl.REGISTER_FAILED, registrationResult);
    verify(userRepository).findByEmail(dto.getEmail());
  }

  @Test
  public void register_whenDtoRoleKeyInvalid_thenReturnRegisterFailed_test() {
    // given
    UserDto dto = spy(new UserDto());
    dto.setEmail("buyer@buyer.ro");
    dto.setPassword("password");
    dto.setRoleKey("ROLE_USER");
    dto.setFirstName("John");
    dto.setLastName("Doe");
    dto.setDateOfBirth("2008-08-08");
    dto.setAddress("123 Main St");
    dto.setContact("1234567890");
    //
    String dtoPassword = dto.getPassword();
    //
    setUpUserServiceImplRepository();
    //
    when(userRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty());
    when(passwordEncoder.encode(dtoPassword)).thenReturn(new BCryptPasswordEncoder().encode(dtoPassword));
    // when
    String registrationResult = userService.register(dto);
    // then
    assertEquals(UserServiceImpl.REGISTER_FAILED, registrationResult);
    verify(userRepository).findByEmail(dto.getEmail());
    verify(passwordEncoder).encode(dtoPassword);
    verify(dto).getRoleKey();
    verify(profileRepository, never()).save(any());
    verify(cartRepository, never()).save(any());
    verify(userRepository, never()).save(any());
  }
}
