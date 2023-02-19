package com.example.bookstore.controllers;

import com.example.bookstore.entities.Book;
import com.example.bookstore.enums.Category;
import com.example.bookstore.exceptions.BookAlreadyExistWithNameException;
import com.example.bookstore.exceptions.InvalidBookIdException;
import com.example.bookstore.models.CreateBookRequest;
import com.example.bookstore.models.SimpleResponse;
import com.example.bookstore.services.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/bookStore")
@Validated
public class BookStoreController {

    private final BookStoreService bookStoreService;

    @Autowired
    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @PostMapping
    public Book addBook(@RequestBody @Valid CreateBookRequest request) throws BookAlreadyExistWithNameException {
        return this.bookStoreService.addBook(request);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteBookById(@PathVariable Integer id) throws InvalidBookIdException {
        return this.bookStoreService.deleteBookById(id);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Integer id) throws InvalidBookIdException {
        return this.bookStoreService.getBookById(id);
    }

    @GetMapping("/byCategory")
    public List<Book> getBooksByCategory(@RequestParam @Valid Category category) {
        return this.bookStoreService.getBooksByCategory(category);
    }

    @GetMapping("/byAuthor/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return this.bookStoreService.getBooksByAuthor(author);
    }

    @GetMapping("/all")
    public List<Book> getAllBooks() {
        return this.bookStoreService.getAllBooks();
    }
}
