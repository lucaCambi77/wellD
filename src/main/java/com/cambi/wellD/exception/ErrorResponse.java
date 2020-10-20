package com.cambi.wellD.exception;

import lombok.Builder;

import java.util.Date;

@Builder
public class ErrorResponse {

    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
}
