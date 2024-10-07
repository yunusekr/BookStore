package com.example.bookstore.service;

import com.example.bookstore.entity.Role;
import com.example.bookstore.entity.User;
import com.example.bookstore.exceptions.BookStoreException;
import com.example.bookstore.repository.RoleRepository;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        if(roleRepository.findByAuthority("USER").isPresent()){
            Role userRole = roleRepository.findByAuthority("USER").get();
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setPassword(encodedPassword);
            user.setAuthorities(roles);
        }else{
            throw new BookStoreException("Role not found", HttpStatus.BAD_REQUEST);
        }
        return userRepository.save(user);
    }
}
