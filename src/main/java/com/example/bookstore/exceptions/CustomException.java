package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;


public class CustomException extends Exception {
    private String errorCode;
    private HttpStatus httpStatus;

    public CustomException(String message, String errorCode, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "errorCode='" + errorCode + '\'' +
                ", message=" + super.getMessage() + '\'' +
                '}';
    }
}
