package com.example.bookstore.controller;

import com.example.bookstore.entity.Product;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.AuthenticationService;
import com.example.bookstore.service.ProductService;
import com.example.bookstore.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ProductService productService;

    @MockBean
    private AuthenticationService authenticationService;

    @Test

    void addFavourite()  {

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
    @WithMockUser(username  = "Ahmet", roles = {"USER"})
    void findUserById() throws Exception {
        User user = new User();
        user.setId(20L);
        user.setUsername("Ahmet");
        user.setEmail("ahmet@hotmail.com");
        user.setPhone("05426754432");
        user.setPassword("deneme");

        when(userService.findUserById(user.getId())).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", user.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}