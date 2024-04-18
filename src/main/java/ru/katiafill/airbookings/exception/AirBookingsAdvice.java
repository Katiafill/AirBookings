package ru.katiafill.airbookings.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AirBookingsAdvice {

    @Data
    @AllArgsConstructor
    public static class ResponseError {
        private String error;
    }
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ResponseError> handlerException(DatabaseException ex) {
        log.error(ex.getLocalizedMessage(), ex);
        return new ResponseEntity<>(new ResponseError(ex.getLocalizedMessage()), HttpStatus.OK);
    }
}
