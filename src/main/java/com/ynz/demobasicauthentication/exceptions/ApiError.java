package com.ynz.demobasicauthentication.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data

public class ApiError {
    private String msg;
    private String status;

    @Builder
    public ApiError(String message, HttpStatus httpStatus) {
        this.msg = message;
        this.status = httpStatus.toString();
    }
}
