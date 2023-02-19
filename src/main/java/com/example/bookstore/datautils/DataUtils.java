package com.example.bookstore.datautils;

import com.example.bookstore.entities.Book;
import com.example.bookstore.enums.Category;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataUtils {

    public static Integer ID = 10000;

    public static Map<String, Integer> BOOK_NAME_AND_BOOK_ID = new HashMap<>();
    public static Map<Category, Set<Integer>> CATEGORY_AND_BOOK_IDS = new HashMap<>();
    public static Map<String , Set<Integer>> AUTHOR_AND_BOOK_IDS = new HashMap<>();


    public static Map<Integer, Book> BOOK_ID_AND_BOOK_DATA = new HashMap<>();
}
