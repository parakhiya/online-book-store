package com.example.bookstore.exceptions;

import org.springframework.http.HttpStatus;

import static com.example.bookstore.exceptions.ErrorCodes.BOOK_NOT_PRESENT;

public class BookNotPresentException extends CustomException {
    public static final String MESSAGE = "Book not present with book id: %d";

    public BookNotPresentException(Integer id) {
        super(String.format(MESSAGE, id), BOOK_NOT_PRESENT, HttpStatus.NOT_FOUND);
    }
}