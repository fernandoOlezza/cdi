package com.exercice.cdi.exception;

public class UserNotFoundException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  String errorMessage;
  public UserNotFoundException(String errorMessage) {
    this.errorMessage = errorMessage;
  }
  public String getErrorMessage() {return errorMessage;}
}
