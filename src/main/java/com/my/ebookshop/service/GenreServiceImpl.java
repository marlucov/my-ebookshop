package com.my.ebookshop.service;

import com.my.ebookshop.dto.GenreDto;
import com.my.ebookshop.repository.GenreRepository;
import com.my.ebookshop.repository.entity.Genre;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GenreServiceImpl
  extends ServiceImpl<GenreRepository, GenreDto, Genre, UUID>
  implements GenreService {
  //
  @Override
  public GenreDto toDto(Genre genre) {
    GenreDto result = new GenreDto();
    result.setId(String.valueOf(genre.getId()));
    result.setType(genre.getType());
    return result;
  }

  @Override
  public Genre fromDto(GenreDto dto) {
    try {
      return getRepository().findById(UUID.fromString(dto.getId())).orElseThrow();
    }
    catch (IllegalArgumentException | NoSuchElementException exception) {
      Genre result = new Genre();
      result.setType(dto.getType());
      return result;
    }
  }
}
