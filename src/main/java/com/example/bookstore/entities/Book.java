package com.example.bookstore.entities;

import com.example.bookstore.enums.Category;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "books")
@TypeDefs(value = {
        @TypeDef(name = "string-array", typeClass = StringArrayType.class,
                defaultForType = String[].class)
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Type(type = "string-array")
    @Column(name = "authors", columnDefinition = "text[]")
    private String[] authors;

    @Type(type = "string-array")
    @Column(name = "categories", columnDefinition = "text[]")
    private Category[] categories;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
