package com.example.bookstore.controller;

import com.example.bookstore.dto.*;
import com.example.bookstore.entity.Product;
import com.example.bookstore.entity.User;
import com.example.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("favourites/{userid}/{productid}")
    public ProductDto addFavourite(@PathVariable(value = "userid") long userid, @PathVariable(value = "productid") long productid){
        Product product = userService.addFavourite(userid,productid);
        return new ProductDto(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getPagenumber(),
                product.getStock(), product.getProduct_image(),new CategoryDto(product.getCategory().getId(),product.getCategory().getName()),new AuthorDto(product.getAuthor().getId(),product.getAuthor().getFullname()));
    }

    @PostMapping("carts/{userid}/{productid}")
    public ProductDto addCart(@PathVariable(value = "userid") long userid, @PathVariable(value = "productid") long productid){
        Product product = userService.addCart(userid,productid);
        return new ProductDto(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getPagenumber(),
                product.getStock(), product.getProduct_image(),new CategoryDto(product.getCategory().getId(),product.getCategory().getName()),new AuthorDto(product.getAuthor().getId(),product.getAuthor().getFullname()));
    }

    @GetMapping("favourites/{userid}")
    public List<ProductDto> getUserFavourites(@PathVariable long userid){
        return userService.getUserFavourites(userid).stream().map(product -> new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getPagenumber(),
                product.getStock(),
                product.getProduct_image(),new CategoryDto(product.getCategory().getId(),product.getCategory().getName()),new AuthorDto(product.getAuthor().getId(),product.getAuthor().getFullname()))).collect(Collectors.toList());
    }

    @GetMapping("carts/{userid}")
    public List<ProductDto> getUserCarts(@PathVariable long userid){
        return userService.getUserCarts(userid).stream().map(product -> new ProductDto(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getPagenumber(),
                product.getStock(),
                product.getProduct_image(),new CategoryDto(product.getCategory().getId(),product.getCategory().getName()),new AuthorDto(product.getAuthor().getId(),product.getAuthor().getFullname()))).collect(Collectors.toList());
    }

    @GetMapping("/{userid}")
    public UserDto findUserById(@PathVariable long userid){
        User user = userService.findUserById(userid);
        return new UserDto(user.getId(),user.getUsername(),user.getEmail(),user.getPhone(),user.getPassword());
    }

}
