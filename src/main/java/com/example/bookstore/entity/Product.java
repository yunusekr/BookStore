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
@Table(name = "products", schema = "public")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @NotNull(message = "name cant be null")
    @NotBlank(message = "name cant be blank")
    private String name;

    @NotNull(message = "description cant be null")
    @NotBlank(message = "description cant be blank")
    private String description;

    @NotNull(message = "price cant be null")
    private Double price;

    @NotNull(message = "pagenumber cant be null")
    private Integer pagenumber;

    @NotNull(message = "stock cant be null")
    private Integer stock;

    @NotNull(message = "product_image cant be null")
    @NotBlank(message = "product_image cant be blank")
    private String product_image;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonBackReference(value = "author-reference")
    private Author author;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "category-reference")
    private Category category;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "favourites", schema = "public",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userfavList = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH})
    @JoinTable(name = "cart", schema = "public",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> usercarList = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "product")
    @JsonManagedReference(value = "product")
    private List<UserProductsComments> commentsList = new ArrayList<>();

}
