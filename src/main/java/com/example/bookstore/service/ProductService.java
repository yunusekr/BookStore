package com.example.bookstore.service;

import com.example.bookstore.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();
    Product findByIdProduct(long id);
    Product saveProduct(Product product);
    Product deleteProduct(long id);
    Product updateProduct(long id,Product product);
}
