package com.inntri.support.utils.exceptions;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Validator {

  public Long validateId(Long id) throws ValidationException {
    if(id < 0){
      throw new ValidationException("Invalid ID value");
    }
    return id;
  }
}
