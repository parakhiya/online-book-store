package com.example.bookstore.models;

import com.example.bookstore.enums.Category;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CreateBookRequest {

    @NotBlank
    private String title;

    @NotEmpty
    private String[] authors;

    @NotEmpty
    private Category[] categories;

    private String description;

    @NotNull
    @Positive
    private Double price;
}
