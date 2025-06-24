package com.exercice.cdi.exception;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ErrorDetail {
  Timestamp timestamp;
  Integer codigo;
  String detail;
}
