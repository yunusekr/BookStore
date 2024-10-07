package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "address",schema = "public")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;


    @NotNull(message = "city cant be null")
    @NotBlank(message = "city cant be blank")
    private String city;

    @NotNull(message = "district cant be null")
    @NotBlank(message = "district cant be blank")
    private String district;

    @NotNull(message = "buildno cant be null")
    @NotBlank(message = "buildno cant be blank")
    private String buildno;

    @NotNull(message = "aptno cant be null")
    @NotBlank(message = "aptno cant be blank")
    private String aptno;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name = "users_address", schema = "public",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

}
