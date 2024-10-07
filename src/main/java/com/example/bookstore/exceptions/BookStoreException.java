package com.example.bookstore.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookStoreException extends RuntimeException {
    private HttpStatus status;

    public BookStoreException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
