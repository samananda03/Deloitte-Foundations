package com.cloudthat.bankingapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "products") // Optional: Adding table name if needed
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL-compatible ID generation strategy
    private Long id;

    @NotBlank
    private String productName;

    @Min(value = 1)
    private double price;

    @Enumerated(EnumType.STRING) // Using STRING instead of ORDINAL for safer enum persistence
    private Category category;

    // Constructors, Getters, and Setters
    public Product() {}

    public Product(String productName, double price, Category category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
