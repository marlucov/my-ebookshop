package com.my.ebookshop.service;

import com.my.ebookshop.dto.AuthorDto;
import com.my.ebookshop.repository.AuthorRepository;
import com.my.ebookshop.repository.entity.Author;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AuthorServiceImpl
  extends ServiceImpl<AuthorRepository, AuthorDto, Author, UUID>
  implements AuthorService {
  //
  @Override
  public AuthorDto toDto(Author author) {
    AuthorDto result = new AuthorDto();
    result.setId(String.valueOf(author.getId()));
    result.setFirstName(author.getFirstName());
    result.setLastName(author.getLastName());
    result.setDateOfBirth(author.getDateOfBirth().toString());
    result.setNationality(author.getNationality());
    return result;
  }

  @Override
  public Author fromDto(AuthorDto dto) {
    try {
      return getRepository().findById(UUID.fromString(dto.getId())).orElseThrow();
    }
    catch (IllegalArgumentException | NoSuchElementException exception) {
      Author result = new Author();
      result.setFirstName(dto.getFirstName());
      result.setLastName(dto.getLastName());
      try {
        result.setDateOfBirth(LocalDate.parse(dto.getDateOfBirth()));
      }
      catch (DateTimeParseException ex) {
        result.setDateOfBirth(null);
      }
      result.setNationality(dto.getNationality());
      return result;
    }
  }
}
