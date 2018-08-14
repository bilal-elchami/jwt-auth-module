package com.live.bilalchami.jwtauthcomponent.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends Exception {

    private String message;
    private final HttpStatus httpStatus;

    public UsernameAlreadyExistsException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
