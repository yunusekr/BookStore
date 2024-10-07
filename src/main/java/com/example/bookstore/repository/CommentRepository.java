package com.example.bookstore.repository;

import com.example.bookstore.entity.Comment;
import com.example.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findByIdUserForComments(long id);
}
