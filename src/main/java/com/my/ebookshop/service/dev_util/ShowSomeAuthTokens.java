package com.my.ebookshop.service.dev_util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ShowSomeAuthTokens {
  public static void main(String[] args) {
    Arrays.stream(new String[]{
        "admin@admin.ro:password",
        "buyer@buyer.ro:password",
        "advisor@advisor.ro:password"
      })
      .forEach(credential ->
        System.out.println(
          "Basic " + Base64.encodeBase64String(credential.getBytes(StandardCharsets.US_ASCII))
        ));
  }
}
