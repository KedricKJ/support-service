package com.inntri.support.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(final String errorMessage, final Throwable errorObject) {
    super(errorMessage, errorObject);
  }

  public EntityNotFoundException(final String errorMessage) {
    super(errorMessage);
  }
}
