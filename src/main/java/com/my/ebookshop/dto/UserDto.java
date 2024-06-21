package com.my.ebookshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
  private String id;
  private String username;
  private String email;
  private String password;
  private String roleKey;
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  private String address;
  private String contact;
}
