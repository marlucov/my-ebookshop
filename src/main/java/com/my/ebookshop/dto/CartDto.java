package com.my.ebookshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
  private BookDto[] bookDtoList;
}
