package com.example.bookstore.service;

import com.example.bookstore.entity.Author;
import com.example.bookstore.entity.Category;
import com.example.bookstore.entity.Product;
import com.example.bookstore.entity.User;
import com.example.bookstore.repository.UserRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductService productService;


    @BeforeEach
    void setUp() {



        Author author = new Author();
        author.setFullname("Deneme");

        Category category = new Category();
        category.setName("MACERA");

        Product product = new Product();
        product.setId(200L);
        product.setName("Hobbit");
        product.setDescription("Sürükleyici Macera olan hobbit yüzüğün ilk zamanlarını anlatıyor");
        product.setPrice(200.0);
        product.setPagenumber(500);
        product.setStock(200);
        product.setAuthor(author);
        product.setCategory(category);
        productService.saveProduct(product);

        User user = new User();
        user.setUsername("Ahmet");
        user.setEmail("ahmet@hotmail.com");
        user.setPhone("05426754432");
        user.setPassword("deneme");
        userRepository.save(user);

    }

    @AfterEach
    void tearDown() {
        if(userRepository.findUserByEmail("ahmet@hotmail.com").isPresent()){
            User foundUser = userRepository.findUserByEmail("ahmet@hotmail.com").get();
            userRepository.delete(foundUser);
            productService.deleteProduct(200L);
        }

    }

    @Test
    void loadUserByUsername() {
    }

    @Test
    void addFavourite() {
        if(userRepository.findUserByEmail("ahmet@hotmail.com").isPresent()){
            User foundUser = userRepository.findUserByEmail("ahmet@hotmail.com").get();
            Product foundProduct = productService.findByIdProduct(200L);
            verify(userService).addFavourite(foundUser.getId(), foundProduct.getId());
        }

    }

    @Test
    void addCart() {
    }

    @Test
    void getUserFavourites() {
    }

    @Test
    void getUserCarts() {
    }

    @Test
    void findUserById() {
    }
}