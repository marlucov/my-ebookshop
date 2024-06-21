package com.my.ebookshop.controller;

import com.my.ebookshop.config.WebResources;
import com.my.ebookshop.dto.UserDto;
import com.my.ebookshop.service.UserService;
import com.my.ebookshop.service.validator.UserDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private UserDtoValidator userDtoValidator;

  @GetMapping(WebResources.ENDPOINT_LOGIN)
  public String getLoginPage() {
    return WebResources.SOURCE_LOGIN;
  }

  @GetMapping(WebResources.ENDPOINT_FORGOT_PASSWORD)
  public String getForgotPassword(RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("forgotPasswordMessage",
      "Forgot password !!! Sorry, can't help you with that !"
    );
    return "redirect:" + WebResources.ENDPOINT_LOGIN;
  }

  @GetMapping(WebResources.ENDPOINT_REGISTER)
  public String getRegister(Model model) {
    model.addAttribute("userDto", new UserDto());
    return WebResources.SOURCE_REGISTER;
  }

  @PostMapping(WebResources.ENDPOINT_REGISTER)
  public String postRegister(
    @ModelAttribute("userDto") UserDto userDto,
    BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model
  ) {
    userDtoValidator.validate(userDto, bindingResult);
    if (bindingResult.hasErrors()) {
      return WebResources.SOURCE_REGISTER;
    }
    String registerMessage = userService.register(userDto);
    if (userService.isRegistrationSuccessful(registerMessage)) {
      redirectAttributes.addFlashAttribute("registerMessage", registerMessage);
      return "redirect:" + WebResources.ENDPOINT_REGISTER;
    }
    model.addAttribute("registerMessage", registerMessage);
    return WebResources.SOURCE_REGISTER;
  }

  @GetMapping(WebResources.ENDPOINT_API_HOME_AUTHENTICATED)
  @ResponseBody
  public RedirectView getAuthenticatedToken(
    Authentication authentication, RedirectAttributes redirectAttributes
  ) {
    redirectAttributes.addAttribute("token", userService.getAuthenticatedToken(authentication));
    return new RedirectView(WebResources.HYPERLINK_HOME_AUTHENTICATED);
  }

  @GetMapping(WebResources.ENDPOINT_API_USER_CURRENT)
  @ResponseBody
  public UserDto getAuthenticated(Authentication authentication) {
    return userService.getAuthenticatedDto(authentication);
  }

  @GetMapping(WebResources.ENDPOINT_API_USER_ALL)
  @ResponseBody
  public List<UserDto> getDtoList() {
    return userService.getDtoList();
  }
}
