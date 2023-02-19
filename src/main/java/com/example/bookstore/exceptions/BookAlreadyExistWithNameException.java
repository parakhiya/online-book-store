package com.example.bookstore.exceptions;


import org.springframework.http.HttpStatus;

import static com.example.bookstore.exceptions.ErrorCodes.BOOK_ALREADY_EXISTS;

public class BookAlreadyExistWithNameException extends CustomException {
    public static final String MESSAGE = "Book already exist with book name: %s";

    public BookAlreadyExistWithNameException(String bookName) {
        super(String.format(MESSAGE, bookName), BOOK_ALREADY_EXISTS, HttpStatus.BAD_REQUEST);
    }
}
