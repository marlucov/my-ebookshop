package com.my.ebookshop.service.dev_util;

import com.my.ebookshop.repository.entity.util.BookGenreNames;
import my_sql_password.MySqlPassword;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.UUID;

public class PopulateWithSomeBooks {
  public static void main(String[] args) {
    try (
      Connection connection = DriverManager.getConnection(
        DatasourceNames.URL, DatasourceNames.USER, MySqlPassword.get()
      );
      PreparedStatement insertGenre = connection.prepareStatement(
        "INSERT INTO genres (id, type) VALUES (?, ?)"
      );
      PreparedStatement insertBook = connection.prepareStatement(
        "INSERT INTO books (id, title, publishing_year, publishing_house_id, price) VALUES (?, ?, ?, ?, ?)"
      );
      PreparedStatement insertPublishingHouse = connection.prepareStatement(
        "INSERT INTO publishing_houses (id, name, year_of_establishment, contact) VALUES (?, ?, ?, ?)"
      );
      PreparedStatement insertBookGenre = connection.prepareStatement(
        "INSERT INTO " + BookGenreNames.NAME + " ("
          + BookGenreNames.BOOK_ID_COLUMN_NAME + ", " + BookGenreNames.GENRE_ID_COLUMN_NAME
          + ") VALUES (?, ?)"
      );
    ) {
      UUID[] publishingHouseIds = new UUID[]{
        UUID.randomUUID()
      };
      Arrays.stream(new String[][]{
          {"0", "New York Times", "1970", "New York"}
        })
        .forEach(publishingHouse -> {
          try {
            insertPublishingHouse.setBytes(1,
              PopulateCategories.uuidToSqlBinary(publishingHouseIds[Integer.parseInt(publishingHouse[0])]));
            insertPublishingHouse.setString(2, publishingHouse[1]);
            insertPublishingHouse.setInt(3, Integer.parseInt(publishingHouse[2]));
            insertPublishingHouse.setString(4, publishingHouse[3]);
            insertPublishingHouse.executeUpdate();
          }
          catch (SQLException exception) {
            throw new RuntimeException(exception);
          }
        });
      //
      UUID[] bookIds = new UUID[]{
        UUID.randomUUID(), UUID.randomUUID()
      };
      Arrays.stream(new String[][]{
          {"0", "Harap Alb", "1978", "10.1", "0"},
          {"1", "Art of War", "2002", "5.39", "0"}
        })
        .forEach(book -> {
          try {
            insertBook.setBytes(1,
              PopulateCategories.uuidToSqlBinary(bookIds[Integer.parseInt(book[0])]));
            insertBook.setString(2, book[1]);
            insertBook.setInt(3, Integer.parseInt(book[2]));
            insertBook.setFloat(5, Float.parseFloat(book[3]));
            insertBook.setBytes(4,
              PopulateCategories.uuidToSqlBinary(publishingHouseIds[Integer.parseInt(book[4])]));
            insertBook.executeUpdate();
          }
          catch (SQLException exception) {
            throw new RuntimeException(exception);
          }
        });
      //
      UUID[] genreIds = new UUID[]{
        UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()
      };
      Arrays.stream(new String[][]{
          {"0", "fantasy"},
          {"1", "fairy-tale"},
          {"2", "strategy"}
        })
        .forEach(genre -> {
          try {
            insertGenre.setBytes(1,
              PopulateCategories.uuidToSqlBinary(genreIds[Integer.parseInt(genre[0])]));
            insertGenre.setString(2, genre[1]);
            insertGenre.executeUpdate();
          }
          catch (SQLException exception) {
            throw new RuntimeException(exception);
          }
        });
      //
      Arrays.stream(new String[][]{
          {"0", "0"},
          {"0", "1"},
          {"1", "2"}
        })
        .forEach(bookGenre -> {
          try {
            insertBookGenre.setBytes(1,
              PopulateCategories.uuidToSqlBinary(bookIds[Integer.parseInt(bookGenre[0])]));
            insertBookGenre.setBytes(2,
              PopulateCategories.uuidToSqlBinary(genreIds[Integer.parseInt(bookGenre[1])]));
            insertBookGenre.executeUpdate();
          }
          catch (SQLException exception) {
            throw new RuntimeException(exception);
          }
        });
    }
    catch (
      SQLException exception) {
      throw new RuntimeException(exception);
    }
  }
}
