package me.dio.sdw2024.adapters.in.exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import me.dio.sdw2024.domain.exceptions.ChampionNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ChampionNotFoundException.class)
  public ResponseEntity<ApiError> handleDomainException(ChampionNotFoundException domainError) {
    return ResponseEntity.unprocessableEntity().body(new ApiError(domainError.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleDomainException(Exception unexpectedError) {
    String message = "Ops, ocorreu um erro na nossa parte.";
    logger.error(message, unexpectedError);
    return ResponseEntity.internalServerError().body(new ApiError(message));
  }

  public record ApiError(String message) {

  }
}
