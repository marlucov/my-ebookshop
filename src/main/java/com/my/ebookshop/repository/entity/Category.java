package com.my.ebookshop.repository.entity;

import com.my.ebookshop.repository.entity.util.CategoryNames;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity(name = CategoryNames.NAME)
public class Category {
  @Id
  @GeneratedValue
  @Column(name = CategoryNames.ID_COLUMN_NAME)
  private UUID id;
  @Column(name = CategoryNames.NAME_COLUMN_NAME)
  private String name;
}
