package com.my.ebookshop.repository.entity;

import com.my.ebookshop.repository.entity.util.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity(name = "users")
public class User {
  @Id
  @GeneratedValue
  private UUID id;
  private String username;
  private String email;
  private String password;
  private String token;
  @Enumerated(EnumType.STRING)
  private UserRole role;
  @OneToOne
  private Profile profile;
  @OneToOne
  private Cart cart;
}
