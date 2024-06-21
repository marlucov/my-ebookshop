package com.my.ebookshop.service;

import com.my.ebookshop.dto.BookDto;
import com.my.ebookshop.dto.CartDto;
import com.my.ebookshop.repository.BookRepository;
import com.my.ebookshop.repository.CartRepository;
import com.my.ebookshop.repository.entity.Book;
import com.my.ebookshop.repository.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CartServiceImpl implements CartService {
  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private BookRepository bookRepository;
  @Autowired
  private BookService bookService;

  @Override
  public CartDto getDto(Authentication authentication) {
    Cart cart = userService.getByEmailOrUsername(authentication.getName()).getCart();
    CartDto result = new CartDto();
    result.setBookDtoList(
      cart.getBooks().stream().map(book -> bookService.toDto(book)).toArray(BookDto[]::new)
    );
    return result;
  }

  @Override
  public void addBook(Authentication authentication, String bookId) {
    Cart cart = userService.getByEmailOrUsername(authentication.getName()).getCart();
    List<Book> books = cart.getBooks();
    UUID uuid = UUID.fromString(bookId);
    if (books.stream().noneMatch(book -> book.getId().equals(uuid))) {
      books.add(bookRepository.findById(uuid).get());
      cartRepository.save(cart);
    }
  }

  @Override
  public void removeBook(Authentication authentication, String bookId) {
    Cart cart = userService.getByEmailOrUsername(authentication.getName()).getCart();
    cart.getBooks().removeIf(book -> book.getId().toString().equals(bookId));
    cartRepository.save(cart);
  }
}
