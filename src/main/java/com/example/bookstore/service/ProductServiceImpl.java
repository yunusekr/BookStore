package com.example.bookstore.service;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Category;
import com.example.bookstore.entity.Product;
import com.example.bookstore.exceptions.BookStoreException;
import com.example.bookstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findByIdProduct(long id) {
        Optional<Product> foundProduct = productRepository.findById(id);
        if(foundProduct.isPresent()) {
            return productRepository.findById(id).orElse(null);
        }else{
            throw new BookStoreException("Product not found", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Product saveProduct(Product product) {
        if(productRepository.findByName(product.getCategory().getName()).isPresent()){
            Category category1 = productRepository.findByName(product.getCategory().getName()).get();
            product.setCategory(category1);
        }
        if(productRepository.findByFullName(product.getAuthor().getFullname()).isPresent()){
            Author author1 = productRepository.findByFullName(product.getAuthor().getFullname()).get();
            product.setAuthor(author1);
        }

        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(long id) {
        Product deletedProduct = findByIdProduct(id);
        productRepository.delete(deletedProduct);
        return deletedProduct;
    }

    @Override
    public Product updateProduct(long id, Product product) {
        if(productRepository.findByName(product.getCategory().getName()).isPresent()){
            Category category1 = productRepository.findByName(product.getCategory().getName()).get();
            product.setCategory(category1);
        }
        if(productRepository.findByFullName(product.getAuthor().getFullname()).isPresent()){
            Author author1 = productRepository.findByFullName(product.getAuthor().getFullname()).get();
            product.setAuthor(author1);
        }
        Product updatedProduct = findByIdProduct(id);
        product.setId(updatedProduct.getId());
        return productRepository.save(product);
    }
}
