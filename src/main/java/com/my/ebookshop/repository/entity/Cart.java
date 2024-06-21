package com.my.ebookshop.repository.entity;

import com.my.ebookshop.repository.entity.util.BookCartNames;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "carts")
public class Cart {
  @Id
  @GeneratedValue
  private UUID id;
  @OneToOne(mappedBy = "cart")
  private User user;
  @ManyToMany
  @JoinTable(
    name = BookCartNames.NAME,
    joinColumns = {@JoinColumn(name = BookCartNames.CART_ID_COLUMN_NAME)},
    inverseJoinColumns = {@JoinColumn(name = BookCartNames.BOOK_ID_COLUMN_NAME)}
  )
  private List<Book> books;
}
