package com.my.ebookshop.controller;

import com.my.ebookshop.config.WebResources;
import com.my.ebookshop.dto.UserDto;
import com.my.ebookshop.repository.entity.util.UserRole;
import com.my.ebookshop.service.UserService;
import com.my.ebookshop.service.UserServiceImpl;
import com.my.ebookshop.service.validator.UserDtoValidator;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {
  @MockBean
  private UserService userService;
  @MockBean
  private UserDtoValidator userDtoValidator;
  @Mock
  private BindingResult bindingResult;
  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser
  public void getDtoList_whenAuthenticated_thenReturnDtoList_withStatusCode200_test() throws Exception {
    UserDto dto = new UserDto();
    dto.setId(UUID.randomUUID().toString());
    dto.setUsername("");
    dto.setEmail("user@user.ro");
    dto.setPassword("password");
    dto.setRoleKey("ROLE_USER");
    dto.setFirstName("John");
    dto.setLastName("Doe");
    dto.setDateOfBirth("1990-01-01");
    dto.setAddress("123 Main St");
    dto.setContact("1234567890");
    List<UserDto> dtoList = List.of(dto);
    when(userService.getDtoList()).thenReturn(dtoList);
    //
    mockMvc.perform(get(WebResources.ENDPOINT_API_USER_ALL))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].id").value(dto.getId()))
      .andExpect(jsonPath("$[0].email").value(dto.getEmail()))
    ;
  }

  @Test
  @WithAnonymousUser
  public void getDtoList_whenNotAuthenticated_thenReturn_withStatusCode401_test() throws Exception {
    //
    mockMvc.perform(get(WebResources.ENDPOINT_API_USER_ALL))
      .andExpect(status().isUnauthorized());
  }

  @Test
  @WithMockUser
  public void postRegister_whenRegistrationSuccessful_thenReturnRedirectToRegisterPage_withStatusCode302_test() throws Exception {
    UserDto givenDto = new UserDto();
    givenDto.setEmail("buyer@buyer.ro");
    givenDto.setPassword("password");
    givenDto.setRoleKey(UserRole.ROLE_BUYER.name());
    givenDto.setFirstName("John");
    givenDto.setLastName("Doe");
    givenDto.setAddress("123 Main St");
    givenDto.setContact("1234567890");
    when(bindingResult.hasErrors()).thenReturn(false);
    when(userService.register(any())).thenReturn(UserServiceImpl.REGISTER_SUCCESSFUL);
    when(userService.isRegistrationSuccessful(any())).thenReturn(true);
    //
    ArgumentCaptor<UserDto> dtoCaptor = ArgumentCaptor.forClass(UserDto.class);
    ArgumentCaptor<BindingResult> bindingResultCaptor = ArgumentCaptor.forClass(BindingResult.class);
    mockMvc.perform(post(WebResources.ENDPOINT_REGISTER)
        .with(csrf())
        .param("email", givenDto.getEmail())
        .param("password", givenDto.getPassword())
        .param("roleKey", givenDto.getRoleKey())
        .param("firstName", givenDto.getFirstName())
        .param("lastName", givenDto.getLastName())
        .param("address", givenDto.getAddress())
        .param("contact", givenDto.getContact())
      )
      .andExpect(status().isFound())
      .andExpect(view().name("redirect:" + WebResources.ENDPOINT_REGISTER))
    ;
    verify(userDtoValidator).validate(dtoCaptor.capture(), bindingResultCaptor.capture());
    assertFalse(bindingResultCaptor.getValue().hasErrors());
    UserDto capturedDto = dtoCaptor.getValue();
    assertEquals(givenDto.getEmail(), capturedDto.getEmail());
    verify(userService).register(capturedDto);
  }

  @Test
  @WithMockUser
  public void postRegister_whenRegisterFormNotValidated_thenReturnRegisterPage_withStatusCode200_test() throws Exception {
    UserDto givenDto = new UserDto();
    givenDto.setEmail("buyer@buyer.ro");
    givenDto.setPassword("pass");
    givenDto.setRoleKey(UserRole.ROLE_BUYER.name());
    givenDto.setFirstName("John");
    givenDto.setLastName("Doe");
    givenDto.setAddress("123 Main St");
    givenDto.setContact("1234567890");
    String pwdErrMsg = "Password must be at least 5 characters long !";
    doAnswer(invocation -> {
      BindingResult result = invocation.getArgument(1);
      result.rejectValue("password", "invalid", pwdErrMsg);
      return null;
    }).when(userDtoValidator).validate(any(), any());
    when(bindingResult.hasErrors()).thenReturn(true);
    //
    ArgumentCaptor<UserDto> dtoCaptor = ArgumentCaptor.forClass(UserDto.class);
    ArgumentCaptor<BindingResult> bindingResultCaptor = ArgumentCaptor.forClass(BindingResult.class);
    mockMvc.perform(post(WebResources.ENDPOINT_REGISTER)
        .with(csrf())
        .param("email", givenDto.getEmail())
        .param("password", givenDto.getPassword())
        .param("roleKey", givenDto.getRoleKey())
        .param("firstName", givenDto.getFirstName())
        .param("lastName", givenDto.getLastName())
        .param("address", givenDto.getAddress())
        .param("contact", givenDto.getContact())
      )
      .andExpect(status().isOk())
      .andExpect(view().name(WebResources.SOURCE_REGISTER))
      .andExpect(model().attributeExists("userDto"))
      .andExpect(content().string(containsString(pwdErrMsg)))
    ;
    verify(userDtoValidator).validate(dtoCaptor.capture(), bindingResultCaptor.capture());
    assertEquals(givenDto.getPassword(), dtoCaptor.getValue().getPassword());
    assertEquals(
      pwdErrMsg,
      Objects.requireNonNull(bindingResultCaptor.getValue().getFieldError("password")).getDefaultMessage()
    );
  }

  @Test
  @WithMockUser
  public void postRegister_whenRegistrationFails_thenReturnRegisterPage_withStatusCode200_test() throws Exception {
    UserDto givenDto = new UserDto();
    givenDto.setEmail("buyer@buyer.ro");
    givenDto.setPassword("password");
    givenDto.setRoleKey(UserRole.ROLE_BUYER.name());
    givenDto.setFirstName("John");
    givenDto.setLastName("Doe");
    givenDto.setAddress("123 Main St");
    givenDto.setContact("1234567890");
    when(bindingResult.hasErrors()).thenReturn(false);
    when(userService.register(any())).thenReturn(UserServiceImpl.REGISTER_FAILED);
    when(userService.isRegistrationSuccessful(any())).thenReturn(false);
    //
    ArgumentCaptor<UserDto> dtoCaptor = ArgumentCaptor.forClass(UserDto.class);
    ArgumentCaptor<BindingResult> bindingResultCaptor = ArgumentCaptor.forClass(BindingResult.class);
    mockMvc.perform(post(WebResources.ENDPOINT_REGISTER)
        .with(csrf())
        .param("email", givenDto.getEmail())
        .param("password", givenDto.getPassword())
        .param("roleKey", givenDto.getRoleKey())
        .param("firstName", givenDto.getFirstName())
        .param("lastName", givenDto.getLastName())
        .param("address", givenDto.getAddress())
        .param("contact", givenDto.getContact())
      )
      .andExpect(status().isOk())
      .andExpect(view().name(WebResources.SOURCE_REGISTER))
      .andExpect(model().attributeExists("userDto"))
      .andExpect(model().attributeExists("registerMessage"))
    ;
    verify(userDtoValidator).validate(dtoCaptor.capture(), bindingResultCaptor.capture());
    assertFalse(bindingResultCaptor.getValue().hasErrors());
    UserDto capturedDto = dtoCaptor.getValue();
    assertEquals(givenDto.getEmail(), capturedDto.getEmail());
    verify(userService).register(capturedDto);
  }
}
