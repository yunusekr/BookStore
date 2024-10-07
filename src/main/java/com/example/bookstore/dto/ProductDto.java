package com.example.bookstore.dto;

import com.example.bookstore.entity.Author;

public record ProductDto(long id, String name, String description, Double price, Integer pagenumber, Integer stock, String product_image,CategoryDto categoryDto,AuthorDto authorDto) {
}
