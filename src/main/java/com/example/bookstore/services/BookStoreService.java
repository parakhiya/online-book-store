package com.example.bookstore.services;

import com.example.bookstore.datautils.DataUtils;
import com.example.bookstore.entities.Book;
import com.example.bookstore.enums.Category;
import com.example.bookstore.exceptions.BookAlreadyExistWithNameException;
import com.example.bookstore.exceptions.InvalidBookIdException;
import com.example.bookstore.models.CreateBookRequest;
import com.example.bookstore.models.SimpleResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.bookstore.datautils.DataUtils.*;

@Service
public class BookStoreService {

    public Book addBook(CreateBookRequest request) throws BookAlreadyExistWithNameException {
        Book book = new Book();
        BeanUtils.copyProperties(request, book);
        book.setId(ID++);

        if (BOOK_NAME_AND_BOOK_ID.containsKey(book.getTitle())) {
            throw new BookAlreadyExistWithNameException(book.getTitle());
        }

        BOOK_NAME_AND_BOOK_ID.put(book.getTitle(), book.getId());

        if (!CATEGORY_AND_BOOK_IDS.containsKey(book.getCategory())) {
            CATEGORY_AND_BOOK_IDS.put(book.getCategory(), new HashSet<>());
        }
        CATEGORY_AND_BOOK_IDS.get(book.getCategory()).add(book.getId());

        if (!AUTHOR_AND_BOOK_IDS.containsKey(book.getAuthor())) {
            AUTHOR_AND_BOOK_IDS.put(book.getAuthor(), new HashSet<>());
        }
        AUTHOR_AND_BOOK_IDS.get(book.getAuthor()).add(book.getId());

        BOOK_ID_AND_BOOK_DATA.put(book.getId(), book);
        return book;
    }

    public SimpleResponse deleteBookById(Integer id) throws InvalidBookIdException {
        if (!BOOK_ID_AND_BOOK_DATA.containsKey(id)) {
            throw new InvalidBookIdException(id);
        }

        Book book = BOOK_ID_AND_BOOK_DATA.get(id);

        BOOK_NAME_AND_BOOK_ID.remove(book.getTitle());
        CATEGORY_AND_BOOK_IDS.get(book.getCategory()).remove(book.getId());
        AUTHOR_AND_BOOK_IDS.get(book.getAuthor()).remove(book.getId());
        BOOK_ID_AND_BOOK_DATA.remove(book.getId());

        return new SimpleResponse(1, true);
    }

    public Book getBookById(Integer id) throws InvalidBookIdException {
        if (!BOOK_ID_AND_BOOK_DATA.containsKey(id)) {
            throw new InvalidBookIdException(id);
        }

        return BOOK_ID_AND_BOOK_DATA.get(id);
    }

    public List<Book> getBooksByCategory(Category category) {
        Set<Integer> bookIds = CATEGORY_AND_BOOK_IDS.get(category);
        if (CollectionUtils.isEmpty(bookIds)) {
            return new LinkedList<>();
        }

        return bookIds.stream().map(BOOK_ID_AND_BOOK_DATA::get).collect(Collectors.toList());
    }

    public List<Book> getBooksByAuthor(String author) {
        Set<Integer> bookIds = AUTHOR_AND_BOOK_IDS.get(author);
        if (CollectionUtils.isEmpty(bookIds)) {
            return new LinkedList<>();
        }

        return bookIds.stream().map(BOOK_ID_AND_BOOK_DATA::get).collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return new LinkedList<>(BOOK_ID_AND_BOOK_DATA.values());
    }
}
