package com.example.bookstore.repository;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Category;
import com.example.bookstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT c FROM Category c WHERE c.name=:name") // JPQL
    Optional<Category> findByName(String name);

    @Query("SELECT a FROM Author a WHERE a.fullname=:fullname") // JPQL
    Optional<Author> findByFullName(String fullname);
}
