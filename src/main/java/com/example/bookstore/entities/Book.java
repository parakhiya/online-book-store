package com.example.bookstore.entities;

import javax.persistence.*;

import com.example.bookstore.enums.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    private Category category;

    @Column(name = "description")
    private String description;

    //Here currency is indian
    @Column(name = "price")
    private Long price;
}
