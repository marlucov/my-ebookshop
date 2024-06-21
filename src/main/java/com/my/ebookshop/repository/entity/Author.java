package com.my.ebookshop.repository.entity;

import com.my.ebookshop.repository.entity.util.AuthorBookNames;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "authors")
public class Author {
  @Id
  @GeneratedValue
  private UUID id;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private String nationality;
  @ManyToMany
  @JoinTable(
    name = AuthorBookNames.NAME,
    joinColumns = {@JoinColumn(name = AuthorBookNames.AUTHOR_ID_COLUMN_NAME)},
    inverseJoinColumns = {@JoinColumn(name = AuthorBookNames.BOOK_ID_COLUMN_NAME)}
  )
  private List<Book> books;
}
