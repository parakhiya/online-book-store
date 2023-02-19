package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;

import static com.example.bookstore.exceptions.ErrorCodes.INVALID_BOOK_ID;

public class InvalidBookIdException extends CustomException {
    public static final String MESSAGE = "Invalid book id: %d";

    public InvalidBookIdException(Integer id) {
        super(String.format(MESSAGE, id), INVALID_BOOK_ID, HttpStatus.BAD_REQUEST);
    }
}