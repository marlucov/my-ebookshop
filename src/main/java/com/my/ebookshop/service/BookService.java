package com.my.ebookshop.service;

import com.my.ebookshop.dto.BookDto;
import com.my.ebookshop.repository.entity.Book;

import java.io.IOException;

public interface BookService extends Service<BookDto, Book> {
  void insert(BookDto bookDto);

  void delete(String id);

  byte[] getPages(String id) throws IOException;
}
