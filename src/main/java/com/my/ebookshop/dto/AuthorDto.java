package com.my.ebookshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {
  private String id;
  private String firstName;
  private String lastName;
  private String dateOfBirth;
  private String nationality;
}
