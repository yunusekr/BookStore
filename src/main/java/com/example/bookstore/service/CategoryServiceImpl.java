package com.example.bookstore.service;

import com.example.bookstore.entity.Category;
import com.example.bookstore.entity.Product;
import com.example.bookstore.exceptions.BookStoreException;
import com.example.bookstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getCategoriesProduct(long id) {
        Category foundCategory = categoryRepository.findById(id).orElseThrow(()-> new BookStoreException("Category not found", HttpStatus.BAD_REQUEST));
        return foundCategory.getProducts();
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
