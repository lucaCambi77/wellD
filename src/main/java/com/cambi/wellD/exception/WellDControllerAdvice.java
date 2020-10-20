package com.cambi.wellD.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

public class WellDControllerAdvice {

    @ExceptionHandler(LineException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public
    @ResponseBody
    ResponseEntity<ErrorResponse> writeLineExceptionBadRequestResponse(LineException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(httpStatus.getReasonPhrase())
                .message(ex.getMessage())
                .status(httpStatus.value())
                .timestamp(new Date())
                .build();

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
