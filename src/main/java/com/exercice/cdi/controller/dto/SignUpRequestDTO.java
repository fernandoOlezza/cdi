package com.exercice.cdi.controller.dto;

import lombok.Data;

import java.util.List;
import javax.validation.constraints.Pattern;

@Data
public class SignUpRequestDTO {

    private String name;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
        message = "El correo electrónico no tiene un formato válido")
    private String email;

    @Pattern(regexp = "^(?=(?:[^A-Z]*[A-Z]){1}[^A-Z]*$)(?=(?:[^\\d]*\\d){2}[^\\d]*$)[A-Za-z\\d]{8,12}$",
        message = "La contraseña debe tener entre 8 y 12 caracteres, contener exactamente una letra mayúscula, exactamente dos dígitos y solo letras y números")
    private String password;

    private List<PhoneDTO> phones;
  }

