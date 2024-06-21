package com.my.ebookshop.service;

import com.my.ebookshop.dto.CartDto;
import org.springframework.security.core.Authentication;

public interface CartService {
  CartDto getDto(Authentication authentication);

  void addBook(Authentication authentication, String bookId);

  void removeBook(Authentication authentication, String bookId);
}
