package com.github.l4crito.synchro.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
public class ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<CustomError> handlyMyCustomException(ResponseStatusException e) {
        LOGGER.error("error occurred {}", e);
        return new ResponseEntity<>(new CustomError(e.getStatus(), e.getMessage(), e.getReason()), e.getStatus());
    }


}

@Data
@AllArgsConstructor
class CustomError {
    private HttpStatus status;
    private String error;
    private String message;
}