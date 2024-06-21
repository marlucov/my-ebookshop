package com.my.ebookshop.controller;

import com.my.ebookshop.config.WebResources;
import com.my.ebookshop.dto.AuthorDto;
import com.my.ebookshop.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {
  @Autowired
  private AuthorService authorService;

  @GetMapping(WebResources.ENDPOINT_API_AUTHOR_ALL)
  public List<AuthorDto> getDtoList() {
    return authorService.getDtoList();
  }
}
