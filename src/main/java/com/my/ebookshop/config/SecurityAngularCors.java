package com.my.ebookshop.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class SecurityAngularCors {
  public static void addSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
      .authorizeHttpRequests(auth -> {
        auth.requestMatchers(WebResources.ENDPOINT_API_AUTHOR_ALL).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_BOOK_ADD).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_BOOK_ALL).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_BOOK_DELETE).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_BOOK_INSERT).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_BOOK_PAGES).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_BOOK_REMOVE).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_CART).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_GENRE_ALL).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_HOME_AUTHENTICATED).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_PUBLISHING_HOUSE_ALL).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_USER_CURRENT).authenticated();
        auth.requestMatchers(WebResources.ENDPOINT_API_USER_ALL).authenticated();
      })
    ;
  }
}
