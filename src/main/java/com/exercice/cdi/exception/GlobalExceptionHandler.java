package com.exercice.cdi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ResponseErrorDTO> handleUserNotFound(UserNotFoundException ex) {
    log.error("UserNotFoundException: ", ex);
    return buildErrorResponse(ex.getErrorMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EmailDuplicadoException.class)
  public ResponseEntity<ResponseErrorDTO> handleEmailDuplicado(EmailDuplicadoException ex) {
    log.error("EmailDuplicadoException: ", ex);
    return buildErrorResponse(ex.getErrorMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ResponseErrorDTO> handleGenericException(Exception ex) {
    log.error("Exception: ", ex);
    return buildErrorResponse("Ocurrió un error inesperado. Por favor, contacta al administrador.", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseErrorDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
    log.error("MethodArgumentNotValidException: ", ex);
    List<ErrorDetail> errores = ex.getBindingResult().getFieldErrors().stream().map(error -> {
      ErrorDetail detalle = new ErrorDetail();
      detalle.setTimestamp(new Timestamp(System.currentTimeMillis()));
      detalle.setCodigo(HttpStatus.BAD_REQUEST.value());
      detalle.setDetail("Campo '" + error.getField() + "': " + error.getDefaultMessage());
      return detalle;
    }).collect(Collectors.toList());

    ResponseErrorDTO response = new ResponseErrorDTO();
    response.setError(errores);

    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<ResponseErrorDTO> buildErrorResponse(String message, HttpStatus status) {
    ErrorDetail errorDetail = new ErrorDetail();
    errorDetail.setTimestamp(new Timestamp(System.currentTimeMillis()));
    errorDetail.setCodigo(status.value());
    errorDetail.setDetail(message);

    ResponseErrorDTO responseError = new ResponseErrorDTO();
    responseError.setError(Collections.singletonList(errorDetail));

    return new ResponseEntity<>(responseError, status);
  }
}
