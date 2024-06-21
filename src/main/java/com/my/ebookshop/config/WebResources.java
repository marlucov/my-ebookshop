package com.my.ebookshop.config;

public interface WebResources {
  // endpoints
  String ENDPOINT_ANGULAR_EMBEDDED_ASSETS = "/assets/**";
  String ENDPOINT_API_AUTHOR_ALL = "/api/all_authors";
  String ENDPOINT_API_BOOK_ADD = "/api/add_book";
  String ENDPOINT_API_BOOK_ALL = "/api/all_books";
  String ENDPOINT_API_BOOK_DELETE = "/api/delete_book";
  String ENDPOINT_API_BOOK_INSERT = "/api/insert_book";
  String ENDPOINT_API_BOOK_PAGES = "/api/book_pages";
  String ENDPOINT_API_BOOK_REMOVE = "/api/remove_book";
  String ENDPOINT_API_CART = "/api/cart";
  String ENDPOINT_API_CATEGORY = "/api/category";
  String ENDPOINT_API_GENRE_ALL = "/api/all_genres";
  String ENDPOINT_API_HOME_AUTHENTICATED = "/api/home";
  String ENDPOINT_API_LOGO = "/api/logo";
  String ENDPOINT_API_PUBLISHING_HOUSE_ALL = "/api/all_publishing_houses";
  String ENDPOINT_API_USER_CURRENT = "/api/crt_user";
  String ENDPOINT_API_USER_ALL = "/api/all_users";
  String ENDPOINT_CATEGORY = "/category";
  String ENDPOINT_FORGOT_PASSWORD = "/forgotpassword";
  String ENDPOINT_HOME = "/";
  String ENDPOINT_INFO = "/info";
  String ENDPOINT_LOGIN = "/login";
  String ENDPOINT_REGISTER = "/register";
  // sources
  String HYPERLINK_HOME_AUTHENTICATED = "http://localhost:4200/";
  String SOURCE_HOME = "index";
  String SOURCE_LOGIN = "login";
  String SOURCE_LOGO = "../assets/img/img.jpeg";
  String SOURCE_REGISTER = "register";
}
