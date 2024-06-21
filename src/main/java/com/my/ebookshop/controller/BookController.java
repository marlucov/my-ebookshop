package com.my.ebookshop.controller;

import com.my.ebookshop.config.WebResources;
import com.my.ebookshop.dto.BookDto;
import com.my.ebookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class BookController {
  @Autowired
  private BookService bookService;

  @GetMapping(WebResources.ENDPOINT_API_BOOK_ALL)
  public List<BookDto> getDtoList() {
    return bookService.getDtoList();
  }

  @PostMapping(WebResources.ENDPOINT_API_BOOK_INSERT)
  public ResponseEntity postInsert(@RequestBody BookDto dto) {
    bookService.insert(dto);
    return ResponseEntity.ok().build();
  }

  @DeleteMapping(WebResources.ENDPOINT_API_BOOK_DELETE)
  public ResponseEntity delete(@RequestParam String id) {
    bookService.delete(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping(WebResources.ENDPOINT_API_BOOK_PAGES)
  public ResponseEntity<byte[]> getPages(@RequestParam String id) throws IOException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_PDF);
    return new ResponseEntity<>(bookService.getPages(id), headers, HttpStatus.OK);
  }
}
