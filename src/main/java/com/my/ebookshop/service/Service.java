package com.my.ebookshop.service;

import java.util.List;
import java.util.Optional;

public interface Service<D, T> {
  List<D> getDtoList();

  D toDto(T t);

  T fromDto(D dto);

  Optional<T> getOrInsert(D dto);
}
