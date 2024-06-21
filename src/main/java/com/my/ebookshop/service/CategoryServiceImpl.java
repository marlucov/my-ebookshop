package com.my.ebookshop.service;

import com.my.ebookshop.dto.CategoryDto;
import com.my.ebookshop.repository.CategoryRepository;
import com.my.ebookshop.repository.entity.Category;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryServiceImpl
  extends ServiceImpl<CategoryRepository, CategoryDto, Category, UUID>
  implements CategoryService {
  //
  @Override
  public CategoryDto toDto(Category category) {
    CategoryDto result = new CategoryDto();
    result.setId(category.getId().toString());
    result.setName(category.getName());
    return result;
  }

  @Override
  public Category fromDto(CategoryDto dto) {
    return null;
  }
}
