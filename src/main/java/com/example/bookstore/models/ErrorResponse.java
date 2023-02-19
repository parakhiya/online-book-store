package com.example.bookstore.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private String errorCode;
    private String message;
    private String exception;

    public ErrorResponse(String errorCode, String message, String exception) {
        this.errorCode = errorCode;
        this.message = message;
        this.exception = exception;
    }
}
