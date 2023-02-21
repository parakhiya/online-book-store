package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;

import static com.example.bookstore.exceptions.ErrorCodes.INVALID_BOOK_PAYLOAD;

public class InvalidInputException extends CustomException {
    public static final String MESSAGE = "Invalid payload for book";

    public InvalidInputException() {
        super(MESSAGE, INVALID_BOOK_PAYLOAD, HttpStatus.BAD_REQUEST);
    }
}
