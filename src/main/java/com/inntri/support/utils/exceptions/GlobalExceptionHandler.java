package com.inntri.support.utils.exceptions;


import com.inntri.support.wrapper.BaseResponseWrapper;
import com.inntri.support.wrapper.ExceptionResponseWrapper;
import com.inntri.support.wrapper.ValidationFailureResponseWrapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.auth.login.CredentialExpiredException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /*@ExceptionHandler(EntityIdCryptoException.class)
    public ResponseEntity<BaseResponseWrapper> handleBaseEntityIdCryptoException(EntityIdCryptoException ex,
                                                                                 WebRequest request) {
        return new ResponseEntity<>(
                new ValidationFailureResponseWrapper("any of the entity IDs (given in path variable or request body parameters)",
                        "corruptted entity ID"),
                HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(ComplexValidationException.class)
    public ResponseEntity<BaseResponseWrapper> handleComplexValidationException(ComplexValidationException ex,
                                                                                WebRequest request) {
        if (ex.getValidationFailures() != null) {
            return new ResponseEntity<>(
                    new ValidationFailureResponseWrapper(ex.getValidationFailures()), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(
                    new ValidationFailureResponseWrapper(ex.getField(), ex.getCode()), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<BaseResponseWrapper> handleEntityAlreadyExistsException(ComplexValidationException ex,
                                                                                WebRequest request) {
        if (ex.getValidationFailures() != null) {
            return new ResponseEntity<>(
                    new ValidationFailureResponseWrapper(ex.getValidationFailures()), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(
                    new ValidationFailureResponseWrapper(ex.getField(), ex.getCode()), HttpStatus.BAD_REQUEST);
        }
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponseWrapper> handleAuthenticationException(ComplexValidationException ex,
                                                                                WebRequest request) {
        if (ex.getValidationFailures() != null) {
            return new ResponseEntity<>(
                    new ValidationFailureResponseWrapper(ex.getValidationFailures()), HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(
                    new ValidationFailureResponseWrapper(ex.getField(), ex.getCode()), HttpStatus.UNAUTHORIZED);
        }
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<BaseResponseWrapper> handleEntityNotFound(
            EntityNotFoundException ex) {
        return new ResponseEntity<>(
                new ExceptionResponseWrapper(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PermissionException.class)
    public ResponseEntity<BaseResponseWrapper> handlePermissionException(PermissionException ex,
                                                                                WebRequest request) {

        System.out.println("Permission exception occured");
        if (ex.getValidationFailures() != null) {
            return new ResponseEntity<>(
                    new ValidationFailureResponseWrapper(ex.getValidationFailures()), HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>(
                    new ValidationFailureResponseWrapper(ex.getField(), ex.getCode()), HttpStatus.FORBIDDEN);
        }
    }

    //@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<BaseResponseWrapper> handleCredentialException(PermissionException ex,
                                                                         WebRequest request) {

        System.out.println("Permission exception occured");
        if (ex.getValidationFailures() != null) {
            return new ResponseEntity<>(
                    new ValidationFailureResponseWrapper(ex.getValidationFailures()), HttpStatus.FORBIDDEN);
        } else {
            return new ResponseEntity<>(
                    new ValidationFailureResponseWrapper(ex.getField(), ex.getCode()), HttpStatus.FORBIDDEN);
        }
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> sqlError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some SQL exception occured");
    }

    @ExceptionHandler(HrmsServiceException.class)
    public ResponseEntity<?> handleHrmsServiceException(HrmsServiceException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(ex.getMessage());
        //return ResponseEntity.internalServerError().body(customError);


    }

    /*protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationFailureResponseWrapper validationFailureResponseWrapper = popolateFieldValidationErrors(fieldErrors);
        return new ResponseEntity<>(new ValidationFailureResponseWrapper(validationFailureResponseWrapper.getValidationFailures()), HttpStatus.BAD_REQUEST);

    }*/

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        System.out.println("handleMethodArgumentNotValid ex "+ex.getBody());
        System.out.println("handleMethodArgumentNotValid ex "+ex.getMessage());
        System.out.println("handleMethodArgumentNotValid ex "+ex.getBindingResult());
        System.out.println("handleMethodArgumentNotValid ex "+ex.getLocalizedMessage());
        List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationFailureResponseWrapper validationFailureResponseWrapper = popolateFieldValidationErrors(fieldErrors);
        return new ResponseEntity<>(new ValidationFailureResponseWrapper(validationFailureResponseWrapper.getValidationFailures()), HttpStatus.BAD_REQUEST);
    }

    public ValidationFailureResponseWrapper popolateFieldValidationErrors(List<FieldError> fieldErrors) {
        List<ValidationFailure> ValidationFailures = new ArrayList<>();
        fieldErrors.forEach(fieldError -> {
            ValidationFailures.add(new ValidationFailure(fieldError.getField(),fieldError.getDefaultMessage()));
        });
        ValidationFailureResponseWrapper validationFailureResponseWrapper = new ValidationFailureResponseWrapper(ValidationFailures);
        return validationFailureResponseWrapper;

    }



}
