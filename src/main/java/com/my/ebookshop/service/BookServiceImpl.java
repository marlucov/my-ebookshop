package com.my.ebookshop.service;

import com.my.ebookshop.dto.AuthorDto;
import com.my.ebookshop.dto.BookDto;
import com.my.ebookshop.dto.GenreDto;
import com.my.ebookshop.repository.BookRepository;
import com.my.ebookshop.repository.entity.Author;
import com.my.ebookshop.repository.entity.Book;
import com.my.ebookshop.repository.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl
  extends ServiceImpl<BookRepository, BookDto, Book, UUID>
  implements BookService {
  //
  @Autowired
  private PublishingHouseService publishingHouseService;
  @Autowired
  private AuthorService authorService;
  @Autowired
  private GenreService genreService;

  @Override
  public void insert(BookDto bookDto) throws NullPointerException {
    getRepository().save(fromDto(bookDto));
  }

  @Override
  public void delete(String id) {
    getRepository().deleteById(UUID.fromString(id));
  }

  private static final String DB_FILES_ROOT = Paths.get("src", "main", "db-files").toString();
  private static final String PAGES_PATH = Paths.get(DB_FILES_ROOT, "ebooks", "pages").toString();

  private Path getPagesPath(String id) {
    String pagesFileName = getRepository().findById(UUID.fromString(id)).orElseThrow().getPagesFileName();
    if (null == pagesFileName) {
      return null;
    }
    if (pagesFileName.isEmpty()) {
      return null;
    }
    return Paths.get(PAGES_PATH, id, pagesFileName);
  }

  @Override
  public byte[] getPages(String id) throws IOException {
    Path pagesPath = getPagesPath(id);
    return (null == pagesPath) ? new byte[0] : Files.readAllBytes(pagesPath);
  }

  @Override
  public BookDto toDto(Book book) {
    BookDto result = new BookDto();
    result.setId(String.valueOf(book.getId()));
    result.setTitle(book.getTitle());
    result.setPublishingYear(book.getPublishingYear());
    result.setPublishingHouseDto(publishingHouseService.toDto(book.getPublishingHouse()));
    result.setAuthorDtoList(book.getAuthors().stream()
      .map(author -> authorService.toDto(author))
      .toArray(AuthorDto[]::new)
    );
    result.setGenreDtoList(book.getGenres().stream()
      .map(genre -> genreService.toDto(genre))
      .toArray(GenreDto[]::new)
    );
    result.setPrice(book.getPrice());
    return result;
  }

  @Override
  public Book fromDto(BookDto bookDto) throws NullPointerException {
    Book result = new Book();
    result.setTitle(bookDto.getTitle());
    result.setPublishingYear(bookDto.getPublishingYear());
    result.setPrice(bookDto.getPrice());
    result.setPublishingHouse(publishingHouseService.getOrInsert(bookDto.getPublishingHouseDto())
      .orElseThrow(NullPointerException::new)
    );
    List<Author> authors = new ArrayList<>();
    result.setAuthors(authors);
    for (AuthorDto authorDto : bookDto.getAuthorDtoList()) {
      authors.add(authorService.getOrInsert(authorDto).orElseThrow(NullPointerException::new));
    }
    List<Genre> genres = new ArrayList<>();
    result.setGenres(genres);
    for (GenreDto genreDto : bookDto.getGenreDtoList()) {
      genres.add(genreService.getOrInsert(genreDto).orElseThrow(NullPointerException::new));
    }
    return result;
  }
}
