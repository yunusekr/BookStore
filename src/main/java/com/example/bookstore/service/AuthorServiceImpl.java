package com.example.bookstore.service;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Product;
import com.example.bookstore.exceptions.BookStoreException;
import com.example.bookstore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Product> getproductsAuthor(long id) {
        Author foundAuthor = authorRepository.findById(id).orElseThrow(()-> new BookStoreException("Author not found", HttpStatus.BAD_REQUEST));
        return foundAuthor.getProducts();
    }
}
