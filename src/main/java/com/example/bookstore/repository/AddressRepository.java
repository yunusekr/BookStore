package com.example.bookstore.repository;

import com.example.bookstore.entity.Address;
import com.example.bookstore.entity.Category;
import com.example.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {


    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findByIdUser(long id);
}
