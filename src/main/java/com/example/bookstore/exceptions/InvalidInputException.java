package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;

import static com.example.bookstore.exceptions.ErrorCodes.BOOK_ALREADY_EXISTS;

public class InvalidInputException extends CustomException {
    public static final String MESSAGE = "Invalid payload for book";

    public InvalidInputException() {
        super(MESSAGE, BOOK_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }
}
