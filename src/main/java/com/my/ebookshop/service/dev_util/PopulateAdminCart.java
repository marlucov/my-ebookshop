package com.my.ebookshop.service.dev_util;

import com.my.ebookshop.repository.entity.util.BookCartNames;
import my_sql_password.MySqlPassword;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.*;
import java.util.Arrays;
import java.util.UUID;

public class PopulateAdminCart {
  public static void main(String[] args) {
    try (
      Connection connection = DriverManager.getConnection(
        DatasourceNames.URL, DatasourceNames.USER, MySqlPassword.get()
      );
      PreparedStatement insertBookCart = connection.prepareStatement(
        "INSERT INTO " + BookCartNames.NAME + " ("
          + BookCartNames.BOOK_ID_COLUMN_NAME + ", " + BookCartNames.CART_ID_COLUMN_NAME
          + ") VALUES (?, ?)"
      );
      PreparedStatement getBookIds = connection.prepareStatement(
        "SELECT id FROM books"
      );
      PreparedStatement getAdminCartId = connection.prepareStatement(
        "SELECT cart_id FROM users WHERE email = ?"
      );
    ) {
      getAdminCartId.setString(1, "admin@admin.ro");
      ResultSet resultSet = getAdminCartId.executeQuery();
      resultSet.next();
      byte[] cartId = resultSet.getBytes("cart_id");
      byte[] beCartId = Arrays.copyOf(cartId, cartId.length);
      ByteBuffer byteBuffer = ByteBuffer
        .wrap(beCartId)
        .order(ByteOrder.BIG_ENDIAN);
      for (byte b : cartId) {
        System.out.printf(" %02x", (short)(0x00ff & b));
      }
      System.out.println();
      System.out.println(new UUID(byteBuffer.getLong(0), byteBuffer.getLong(8)).toString());
      resultSet = getBookIds.executeQuery();
      resultSet.next();
      byte[] bookId1 = resultSet.getBytes("id");
      byte[] bebookId1 = Arrays.copyOf(bookId1, bookId1.length);
      ByteBuffer byteBuffer1 = ByteBuffer
        .wrap(bebookId1)
        .order(ByteOrder.BIG_ENDIAN);
      for (byte b : bookId1) {
        System.out.printf(" %02x", (short)(0x00ff & b));
      }
      System.out.println();
      System.out.println(new UUID(byteBuffer1.getLong(0), byteBuffer1.getLong(8)).toString());
      resultSet.next();
      byte[] bookId2 = resultSet.getBytes("id");
      insertBookCart.setBytes(1, bookId1);
      insertBookCart.setBytes(2, cartId);
      insertBookCart.executeUpdate();
      insertBookCart.setBytes(1, bookId2);
      insertBookCart.setBytes(2, cartId);
      insertBookCart.executeUpdate();
    }
    catch (
      SQLException exception) {
      throw new RuntimeException(exception);
    }
  }
}
