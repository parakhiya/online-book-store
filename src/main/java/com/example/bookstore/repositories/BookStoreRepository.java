package com.example.bookstore.repositories;

import com.example.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStoreRepository extends JpaRepository<Book, Integer> {
}
