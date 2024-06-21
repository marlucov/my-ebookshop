package com.my.ebookshop.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public abstract class ServiceImpl<R extends JpaRepository<T, ID>, D, T, ID> implements Service<D, T> {
  @Autowired
  private R repository;

  @Override
  public List<D> getDtoList() {
    List<D> result = new ArrayList<>();
    repository.findAll().forEach(item -> result.add(toDto(item)));
    return result;
  }

  @Override
  public Optional<T> getOrInsert(D dto) {
    try {
      return Optional.of(getRepository().save(fromDto(dto)));
    }
    catch (NullPointerException exception) {
      return Optional.empty();
    }
  }
}
