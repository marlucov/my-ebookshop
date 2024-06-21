package com.my.ebookshop.repository.entity.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
  ROLE_ADMIN("Administrator"),
  ROLE_BUYER("Buyer"),
  ROLE_ADVISOR("Advisor");
  //
  private final String label;
}
