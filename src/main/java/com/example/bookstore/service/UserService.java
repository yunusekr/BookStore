package com.example.bookstore.service;

import com.example.bookstore.entity.Product;
import com.example.bookstore.entity.User;
import com.example.bookstore.exceptions.BookStoreException;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private ProductService productService;

    @Autowired
    public UserService(UserRepository userRepository,ProductService productService) {
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> {
                    System.out.println("User credentials are not valid");
                    throw new UsernameNotFoundException("User credentials are not valid");
                });
    }

    public Product addFavourite(long userid,long productId){
       User foundUser = userRepository.findById(userid).orElseThrow(()-> new BookStoreException("User not found", HttpStatus.BAD_REQUEST));
       Product foundProduct = productService.findByIdProduct(productId);
       foundUser.getFavourites().add(foundProduct);
       userRepository.save(foundUser);
       return foundProduct;
    }

    public Product addCart(long userid,long productId){
        User foundUser = userRepository.findById(userid).orElseThrow(()-> new BookStoreException("User not found", HttpStatus.BAD_REQUEST));
        Product foundProduct = productService.findByIdProduct(productId);
        foundUser.getCartList().add(foundProduct);
        userRepository.save(foundUser);
        return foundProduct;
    }

    public List<Product> getUserFavourites(long id){
        User foundUser = userRepository.findById(id).orElseThrow(()-> new BookStoreException("User not found", HttpStatus.BAD_REQUEST));
        return foundUser.getFavourites();
    }

    public List<Product> getUserCarts(long id){
        User foundUser = userRepository.findById(id).orElseThrow(()-> new BookStoreException("User not found", HttpStatus.BAD_REQUEST));
        return  foundUser.getCartList();
    }

    public User findUserById(long id){
        User foundUser = userRepository.findById(id).orElseThrow(()-> new BookStoreException("User not found", HttpStatus.BAD_REQUEST));
        return foundUser;
    }

}
