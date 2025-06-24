package com.exercice.cdi.exception;

import lombok.Data;

import java.util.List;

@Data
public class ResponseErrorDTO {
  private List<ErrorDetail> error;
}
