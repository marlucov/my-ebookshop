package com.my.ebookshop.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "profiles")
public class Profile {
  @Id
  @GeneratedValue
  private UUID id;
  private String firstName;
  private String lastName;
  private LocalDate dateOfBirth;
  private String address;
  private String contact;
}
