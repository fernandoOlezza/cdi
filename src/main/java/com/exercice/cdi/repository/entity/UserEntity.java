package com.exercice.cdi.repository.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(updatable = false, nullable = false)
  private UUID id;

  private String name;

  @Column(unique = true, nullable = false)
  private String email;

  private String password;

  private LocalDateTime created;

  private LocalDateTime lastLogin;

  private String token;

  private Boolean isActive;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PhoneEntity> phones;
}
