package com.my.ebookshop.repository.entity;

import com.my.ebookshop.repository.entity.util.AuthorBookNames;
import com.my.ebookshop.repository.entity.util.BookGenreNames;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "books")
public class Book {
  @Id
  @GeneratedValue
  private UUID id;
  private String title;
  private Integer publishingYear;
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
  @JoinTable(
    name = AuthorBookNames.NAME,
    joinColumns = {@JoinColumn(name = AuthorBookNames.BOOK_ID_COLUMN_NAME)},
    inverseJoinColumns = {@JoinColumn(name = AuthorBookNames.AUTHOR_ID_COLUMN_NAME)}
  )
  private List<Author> authors;
  @ManyToOne
  private PublishingHouse publishingHouse;
  @ManyToMany
  @JoinTable(
    name = BookGenreNames.NAME,
    joinColumns = {@JoinColumn(name = BookGenreNames.BOOK_ID_COLUMN_NAME)},
    inverseJoinColumns = {@JoinColumn(name = BookGenreNames.GENRE_ID_COLUMN_NAME)}
  )
  private List<Genre> genres;
  private Float price;
  private String pagesFileName;
}
