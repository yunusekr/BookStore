package com.example.bookstore.service;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Product;

import java.util.List;

public interface AuthorService {

    List<Product> getproductsAuthor(long id);

    List<Author> getAllAuthors();

}
