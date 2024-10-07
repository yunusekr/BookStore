package com.example.bookstore.controller;

import com.example.bookstore.dto.AuthorDto;
import com.example.bookstore.dto.CategoryDto;
import com.example.bookstore.dto.ProductDto;
import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Product;
import com.example.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDto> findById(){
        return authorService.getAllAuthors().stream().map(author -> new AuthorDto(
                author.getId(),
                author.getFullname()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<ProductDto> findById(@PathVariable long id){
        return authorService.getproductsAuthor(id).stream().map(product -> new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getPagenumber(),
                product.getStock(),
                product.getProduct_image(),new CategoryDto(product.getCategory().getId(),product.getCategory().getName()),new AuthorDto(product.getAuthor().getId(),product.getAuthor().getFullname()))).collect(Collectors.toList());
    }
}
