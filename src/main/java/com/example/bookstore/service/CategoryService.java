package com.example.bookstore.service;

import com.example.bookstore.entity.Category;
import com.example.bookstore.entity.Product;

import java.util.List;

public interface CategoryService {
    List<Product> getCategoriesProduct(long id);

    List<Category> getAllCategories();
}
