package com.srb.product.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "PT_logProduct")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private double price;

    private String brand;

    private int quantity;

    private boolean active;

    private LocalDateTime creation_time;

    private LocalDateTime lastmodified_time;

    public Product(){}

    public Product(Long id, String name, String description, double price, String brand, int quantity, boolean active, LocalDateTime creation_time, LocalDateTime lastmodified_time) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.active = active;
        this.creation_time = creation_time;
        this.lastmodified_time = lastmodified_time;
    }

    @PrePersist
    public void createTime()
    {
        this.creation_time=LocalDateTime.now();
        this.lastmodified_time=LocalDateTime.now();
    }
    @PreUpdate
    public void updateTime()
    {
        this.lastmodified_time = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDateTime creation_time) {
        this.creation_time = creation_time;
    }

    public LocalDateTime getLastmodified_time() {
        return lastmodified_time;
    }

    public void setLastmodified_time(LocalDateTime lastmodified_time) {
        this.lastmodified_time = lastmodified_time;
    }
}
