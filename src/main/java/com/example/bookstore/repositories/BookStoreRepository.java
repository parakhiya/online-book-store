package com.example.bookstore.repositories;

import com.example.bookstore.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookStoreRepository extends JpaRepository<Book, Integer> {

    @Query(value = "SELECT * FROM books WHERE :category = ANY(categories)", nativeQuery = true)
    Page<Book> findByCategory(String category, Pageable pageable);

    @Query(value = "SELECT * FROM books WHERE :author = ANY(authors)", nativeQuery = true)
    Page<Book> findByAuthor(String author, Pageable pageable);
}
