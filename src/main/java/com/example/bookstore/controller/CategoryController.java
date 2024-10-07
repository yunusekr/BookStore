package com.example.bookstore.controller;

import com.example.bookstore.dto.AuthorDto;
import com.example.bookstore.dto.CategoryDto;
import com.example.bookstore.dto.ProductDto;
import com.example.bookstore.entity.Category;
import com.example.bookstore.entity.Product;
import com.example.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> findAllCategories(){
        return categoryService.getAllCategories().stream().map(category -> new CategoryDto(
                category.getId(),
                category.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<ProductDto> findById(@PathVariable long id){
        return categoryService.getCategoriesProduct(id).stream().map(product -> new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getPagenumber(),
                product.getStock(),
                product.getProduct_image(),new CategoryDto(product.getCategory().getId(),product.getCategory().getName()),new AuthorDto(product.getAuthor().getId(),product.getAuthor().getFullname()))).collect(Collectors.toList());
    }


}
