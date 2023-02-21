package com.example.bookstore.controllers;

import com.example.bookstore.entities.Book;
import com.example.bookstore.enums.Category;
import com.example.bookstore.exceptions.BookAlreadyExistWithNameException;
import com.example.bookstore.exceptions.BookNotPresentException;
import com.example.bookstore.exceptions.InvalidInputException;
import com.example.bookstore.models.CreateBookRequest;
import com.example.bookstore.models.SimpleResponse;
import com.example.bookstore.services.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/bookStore")
@Validated
public class BookStoreController {

    private final BookStoreService bookStoreService;

    @Autowired
    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    /**
     * add book
     *
     * @param request request
     * @return book
     * @throws InvalidInputException             invalid input exception
     * @throws BookAlreadyExistWithNameException book already exist with name exception
     */
    @PostMapping
    public Book addBook(@RequestBody @Valid CreateBookRequest request) throws InvalidInputException, BookAlreadyExistWithNameException {
        return this.bookStoreService.addBook(request);
    }

    /**
     * delete book by id
     *
     * @param id id
     * @return simple response of success
     */
    @DeleteMapping("/{id}")
    public SimpleResponse deleteBookById(@PathVariable Integer id) {
        return this.bookStoreService.deleteBookById(id);
    }

    /**
     * get book by id
     *
     * @param id id
     * @return book
     * @throws BookNotPresentException invalid book id exception
     */
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) throws BookNotPresentException {
        return this.bookStoreService.getBookById(id);
    }

    /**
     * get books by category with pagination
     *
     * @param category category
     * @param pageable pageable
     * @return pagination with books
     */
    @GetMapping("/byCategory")
    public Page<Book> getBooksByCategory(@RequestParam @Valid Category category, Pageable pageable) {
        return this.bookStoreService.getBooksByCategory(category, pageable);
    }

    /**
     * get books by author
     *
     * @param author   author name
     * @param pageable pageable
     * @return books with pageable
     */
    @GetMapping("/byAuthor/{author}")
    public Page<Book> getBooksByAuthor(@PathVariable String author, Pageable pageable) {
        return this.bookStoreService.getBooksByAuthor(author, pageable);
    }

    /**
     * get all books
     *
     * @param pageable pagebale
     * @return all books with pageable
     */
    @GetMapping("/all")
    public Page<Book> getAllBooks(Pageable pageable) {
        return this.bookStoreService.getAllBooks(pageable);
    }
}
