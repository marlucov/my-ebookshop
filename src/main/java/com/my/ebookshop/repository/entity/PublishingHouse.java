package com.my.ebookshop.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "publishing_houses")
public class PublishingHouse {
  @Id
  @GeneratedValue
  private UUID id;
  private String name;
  private Integer yearOfEstablishment;
  private String contact;
}
