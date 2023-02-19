package com.example.bookstore.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleResponse {
    private Integer count;
    private Boolean success;
}
