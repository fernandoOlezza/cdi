package com.exercice.cdi.exception;

public class EmailDuplicadoException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  String errorMessage;
  public EmailDuplicadoException(String errorMessage) {
    this.errorMessage = errorMessage;
  }
  public String getErrorMessage() {return errorMessage;}
}
