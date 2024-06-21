package com.my.ebookshop.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "genres")
public class Genre {
  @Id
  @GeneratedValue
  private UUID id;
  private String type;
}
