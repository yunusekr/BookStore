package com.example.bookstore.repository;

import com.example.bookstore.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUsername("Ahmet");
        user.setEmail("ahmet@hotmail.com");
        user.setPhone("05426754432");
        user.setPassword("deneme");
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        User foundUser = userRepository.findUserByEmail("ahmet@hotmail.com").get();
        userRepository.delete(foundUser);
    }

    @Test
    void findUserByEmail() {
        if(userRepository.findUserByEmail("ahmet@hotmail.com").isPresent()){
            User foundUser = userRepository.findUserByEmail("ahmet@hotmail.com").get();
            assertNotNull(foundUser);
        }
    }
}