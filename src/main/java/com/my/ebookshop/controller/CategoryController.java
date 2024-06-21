package com.my.ebookshop.controller;

import com.my.ebookshop.config.WebResources;
import com.my.ebookshop.dto.CategoryDto;
import com.my.ebookshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {
  @Autowired
  private CategoryService categoryService;

  @GetMapping(WebResources.ENDPOINT_API_CATEGORY)
  public ResponseEntity<List<CategoryDto>> getDtoList() {
    return ResponseEntity.ok(categoryService.getDtoList());
  }
}
