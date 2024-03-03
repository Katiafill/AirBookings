package ru.katiafill.airbookings.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AirBookingsAdvice {

    @Data
    @AllArgsConstructor
    public static class ResponseError {
        private String error;
    }
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ResponseError> handlerException(DatabaseException ex) {
        return new ResponseEntity<>(new ResponseError(ex.getLocalizedMessage()), HttpStatus.OK);
    }
}
