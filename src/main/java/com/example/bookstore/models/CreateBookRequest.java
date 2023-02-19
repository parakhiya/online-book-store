package com.example.bookstore.models;

import com.example.bookstore.enums.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateBookRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull
    private Category category;

    @NotBlank
    private String description;

    @NotNull
    private Long price;
}
