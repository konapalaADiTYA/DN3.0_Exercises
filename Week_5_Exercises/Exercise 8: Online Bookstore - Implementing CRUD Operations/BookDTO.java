package com.example.BookstoreAPI.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BookDTO {

    private Long id;

    @NotNull(message = "Title is required")
    @Size(min = 2, max = 100, message = "Title should be between 2 and 100 characters")
    private String title;

    @NotNull(message = "Author is required")
    @Size(min = 2, max = 100, message = "Author should be between 2 and 100 characters")
    private String author;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price should be a positive number")
    private Double price;

    @NotNull(message = "ISBN is required")
    @Size(min = 10, max = 13, message = "ISBN should be between 10 and 13 characters")
    private String isbn;
}
