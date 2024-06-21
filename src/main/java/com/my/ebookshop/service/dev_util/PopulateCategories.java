package com.my.ebookshop.service.dev_util;

import com.my.ebookshop.repository.entity.util.CategoryNames;
import my_sql_password.MySqlPassword;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.sql.*;
import java.util.Arrays;
import java.util.UUID;

public class PopulateCategories {
  public static void main(String[] args) {
    try (
      Connection connection = DriverManager.getConnection(
        DatasourceNames.URL, DatasourceNames.USER, MySqlPassword.get()
      );
      PreparedStatement preparedStatement = connection.prepareStatement(
        "INSERT INTO " + CategoryNames.NAME + " ("
          + CategoryNames.ID_COLUMN_NAME + ", " + CategoryNames.NAME_COLUMN_NAME
          + ") VALUES (?, ?)"
      );
    ) {
      Arrays.stream(new String[]{
          "Contact",
          "Support",
          "FAQ"
        })
        .forEach(member -> {
          try {
            preparedStatement.setBytes(1, uuidToSqlBinary(UUID.randomUUID()));
            preparedStatement.setString(2, member);
            preparedStatement.executeUpdate();
          }
          catch (SQLException exception) {
            throw new RuntimeException(exception);
          }
        });
    }
    catch (SQLException exception) {
      throw new RuntimeException(exception);
    }
  }

  public static byte[] uuidToSqlBinary(UUID uuid) {
    byte[] result = new byte[16];
    ByteBuffer
      .wrap(result)
      .order(ByteOrder.BIG_ENDIAN)
      .putLong(uuid.getMostSignificantBits())
      .putLong(uuid.getLeastSignificantBits())
    ;
    return result;
  }
}
