package com.my.ebookshop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
  private String id;
  private String title;
  private Integer publishingYear;
  private PublishingHouseDto publishingHouseDto;
  private AuthorDto[] authorDtoList;
  private GenreDto[] genreDtoList;
  private Float price;
}
