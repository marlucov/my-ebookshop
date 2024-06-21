package com.my.ebookshop.service.validator;

import com.my.ebookshop.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class UserDtoValidator {
  public void validate(UserDto userDto, BindingResult bindingResult) {
    final String firstLastNameSeparator = " ";
    final String userName = userDto.getFirstName() + firstLastNameSeparator + userDto.getLastName();
    if (" ".equals(userName)) {
      bindingResult.addError(new FieldError(
        "userDto", "firstName", "Name should not be empty !"
      ));
    }
    else if (!userName.matches("[a-zA-Z]+|([a-zA-Z]+ +)+[a-zA-Z]+")) {
      bindingResult.addError(new FieldError(
        "userDto", "firstName", "Name should only contain letters and space !"
      ));
    }
    if (userDto.getPassword().length() < 5) {
      bindingResult.addError(new FieldError(
        "userDto", "password", "Password must be at least 5 characters long !"
      ));
    }
  }
}
