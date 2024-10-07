package com.example.bookstore.controller;

import com.example.bookstore.dto.AuthorDto;
import com.example.bookstore.dto.CategoryDto;
import com.example.bookstore.dto.ProductDto;
import com.example.bookstore.entity.Product;
import com.example.bookstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> findAll(){
        return  productService.findAllProducts().stream().map(product -> new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getPagenumber(),
                product.getStock(),
                product.getProduct_image(),
                new CategoryDto(product.getCategory().getId(),product.getCategory().getName()),new AuthorDto(product.getAuthor().getId(),product.getAuthor().getFullname()))).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable long id){
        Product foundProduct = productService.findByIdProduct(id);
        return new ProductDto(foundProduct.getId(),foundProduct.getName(),foundProduct.getDescription(),foundProduct.getPrice(),foundProduct.getPagenumber(),
                foundProduct.getStock(),foundProduct.getProduct_image(),new CategoryDto(foundProduct.getCategory().getId(),foundProduct.getCategory().getName()),new AuthorDto(foundProduct.getAuthor().getId(),foundProduct.getAuthor().getFullname()));

    }

    @PostMapping("/admin")
    public ProductDto save(@RequestBody Product product){
        productService.saveProduct(product);
        return new ProductDto(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getPagenumber(),
                product.getStock(),
                product.getProduct_image(),new CategoryDto(product.getCategory().getId(),product.getCategory().getName()),new AuthorDto(product.getAuthor().getId(),product.getAuthor().getFullname()));
    }

    @PutMapping("admin/{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product){
         productService.updateProduct(id, product);
         return product;
    }

    @DeleteMapping("admin/{id}")
    public ProductDto deleteProduct(@PathVariable long id){
         Product deletedProduct = productService.findByIdProduct(id);
         productService.deleteProduct(id);
         return new ProductDto(deletedProduct.getId(),deletedProduct.getName(),deletedProduct.getDescription(),deletedProduct.getPrice(),deletedProduct.getPagenumber(),
                deletedProduct.getStock(),
                 deletedProduct.getProduct_image(),new CategoryDto(deletedProduct.getCategory().getId(),deletedProduct.getCategory().getName()),new AuthorDto(deletedProduct.getAuthor().getId(),deletedProduct.getAuthor().getFullname()));

    }
}