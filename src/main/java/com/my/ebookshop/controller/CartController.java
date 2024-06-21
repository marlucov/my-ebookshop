package com.my.ebookshop.controller;

import com.my.ebookshop.config.WebResources;
import com.my.ebookshop.dto.CartDto;
import com.my.ebookshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
  @Autowired
  private CartService cartService;

  @GetMapping(WebResources.ENDPOINT_API_CART)
  public ResponseEntity<CartDto> getDto(Authentication authentication) {
    return new ResponseEntity<>(cartService.getDto(authentication), HttpStatus.OK);
  }

  @PatchMapping(WebResources.ENDPOINT_API_BOOK_ADD)
  public ResponseEntity addBook(Authentication authentication, @RequestParam String bookId) {
    cartService.addBook(authentication, bookId);
    return ResponseEntity.ok().build();
  }

  @PatchMapping(WebResources.ENDPOINT_API_BOOK_REMOVE)
  public ResponseEntity removeBook(Authentication authentication, @RequestParam String bookId) {
    cartService.removeBook(authentication, bookId);
    return ResponseEntity.ok().build();
  }
}
