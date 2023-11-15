package kz.ruanjian.memed.controller.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErrorHandler {

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(BindException.class)
  public ErrorResponse handleValidationException(BindException bindException) {
    return ErrorResponse.builder()
      .fieldErrors(mapToFieldErrors(bindException))
      .globalErrors(mapToGlobalErrors(bindException))
      .statusCode(HttpStatus.BAD_REQUEST.value())
      .build();
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ErrorResponse handleAccessDeniedException(AccessDeniedException exception) {
    return ErrorResponse.builder()
      .statusCode(HttpStatus.FORBIDDEN.value())
      .message(exception.getMessage())
      .build();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleServiceException(Exception exception) {
    HttpStatus httpStatus = getStatus(exception);

    ErrorResponse errorResponse = ErrorResponse.builder()
      .statusCode(httpStatus.value())
      .message(exception.getMessage())
      .build();

    return ResponseEntity
      .status(httpStatus)
      .body(errorResponse);
  }

  private HttpStatus getStatus(Exception exception) {
    return Optional
      .ofNullable(exception.getClass().getAnnotation(ResponseStatus.class))
      .map(ResponseStatus::value)
      .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private Set<String> mapToGlobalErrors(BindException bindException) {
    return bindException
      .getGlobalErrors()
      .stream()
      .map(ObjectError::getDefaultMessage)
      .collect(Collectors.toSet());
  }

  private Map<String, String> mapToFieldErrors(BindException bindException) {
    return bindException
      .getFieldErrors()
      .stream()
      .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
  }
}
