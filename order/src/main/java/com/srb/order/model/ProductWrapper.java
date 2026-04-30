package com.srb.order.model;

import jakarta.persistence.Column;

public class ProductWrapper {
    private Long id;

    @Column(nullable = false)
    private String name;

    private double price;

    private String brand;

    private int quantity;

    private boolean active;

    public ProductWrapper()
    {

    }

    public ProductWrapper(Long id, String name, double price, String brand, int quantity, boolean active) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.quantity = quantity;
        this.active = active;
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
}
