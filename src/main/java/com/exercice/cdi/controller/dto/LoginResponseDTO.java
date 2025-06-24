package com.exercice.cdi.controller.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class LoginResponseDTO {
  private UUID id;
  private LocalDateTime created;
  private LocalDateTime lastLogin;
  private String token;
  private Boolean isActive;
  private String name;
  private String enamil;
  private String password;
  private List<PhoneDTO> phones;

}
