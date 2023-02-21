package com.example.bookstore.services;

import com.example.bookstore.entities.Book;
import com.example.bookstore.enums.Category;
import com.example.bookstore.exceptions.BookAlreadyExistWithNameException;
import com.example.bookstore.exceptions.BookNotPresentException;
import com.example.bookstore.exceptions.InvalidInputException;
import com.example.bookstore.models.CreateBookRequest;
import com.example.bookstore.models.SimpleResponse;
import com.example.bookstore.repositories.BookStoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.bookstore.constants.ApplicationConstants.BOOK_TITLE_UNIQUE_KEY;

@Slf4j
@Service
public class BookStoreService {

    private final BookStoreRepository bookStoreRepository;

    @Autowired
    public BookStoreService(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    /**
     * add book
     *
     * @param request request
     * @return book
     * @throws InvalidInputException             invalid input exception
     * @throws BookAlreadyExistWithNameException book already exist with name exception
     */
    public Book addBook(CreateBookRequest request) throws InvalidInputException, BookAlreadyExistWithNameException {
        Book book = new Book();
        BeanUtils.copyProperties(request, book);
        return this.persist(book);
    }

    /**
     * delete book by id
     *
     * @param id id
     * @return simple response of success
     */
    public SimpleResponse deleteBookById(Integer id) {
        try {
            this.bookStoreRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Book not present with id for delete operation");
            return new SimpleResponse(null, false);
        }
        return new SimpleResponse(null, true);
    }

    /**
     * get book by id
     *
     * @param id id
     * @return book
     * @throws BookNotPresentException invalid book id exception
     */
    public Book getBookById(Integer id) throws BookNotPresentException {
        return this.bookStoreRepository.findById(id)
                .orElseThrow(() -> new BookNotPresentException(id));
    }

    /**
     * get books by category with pagination
     *
     * @param category category
     * @param pageable pageable
     * @return pagination with books
     */
    public Page<Book> getBooksByCategory(Category category, Pageable pageable) {
        return this.bookStoreRepository.findByCategory(category.name(), pageable);
    }

    /**
     * get books by author
     *
     * @param author   author name
     * @param pageable pageable
     * @return books with pageable
     */
    public Page<Book> getBooksByAuthor(String author, Pageable pageable) {
        return this.bookStoreRepository.findByAuthor(author, pageable);
    }

    /**
     * get all books
     *
     * @param pageable pagebale
     * @return all books with pageable
     */
    public Page<Book> getAllBooks(Pageable pageable) {
        return this.bookStoreRepository.findAll(pageable);
    }

    /**
     * persist book
     *
     * @param book book
     * @return persisted book
     * @throws BookAlreadyExistWithNameException book already exist with name exception
     * @throws InvalidInputException             invalid input exception
     */
    private Book persist(Book book) throws BookAlreadyExistWithNameException, InvalidInputException {
        try {
            return this.bookStoreRepository.save(book);
        } catch (DataIntegrityViolationException dive) {
            Throwable e = dive.getCause();
            if (e instanceof ConstraintViolationException) {
                String constraintName = ((ConstraintViolationException) e).getConstraintName();
                if (constraintName.equals(BOOK_TITLE_UNIQUE_KEY)) {
                    throw new BookAlreadyExistWithNameException(book.getTitle());
                } else {
                    throw new InvalidInputException();
                }
            } else {
                throw dive;
            }
        }
    }
}
