package com.my.ebookshop.controller;

import com.my.ebookshop.config.WebResources;
import com.my.ebookshop.dto.GenreDto;
import com.my.ebookshop.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreController {
  @Autowired
  private GenreService genreService;

  @GetMapping(WebResources.ENDPOINT_API_GENRE_ALL)
  public List<GenreDto> getDtoList() {
    return genreService.getDtoList();
  }
}
