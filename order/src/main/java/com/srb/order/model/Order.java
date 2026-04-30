package com.srb.order.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "PT_logOrders")
public class Order {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long orderId;

    private Long productId;

    private int quantity;

    private double total_price;

    private String status;

    private String userName;

    private Long userId;

    private LocalDateTime creation_time;

    private LocalDateTime lastmodified_time;

    @PrePersist
    public void setDateTime()
    {
        this.creation_time=LocalDateTime.now();
        this.lastmodified_time = LocalDateTime.now();
    }

    @PreUpdate
    public void setLastmodifiedtime()
    {
        this.lastmodified_time= LocalDateTime.now();
    }

    public Order()
    {

    }


    public Order(Long orderId, Long productId, int quantity, double total_price, String status, String userName, Long userId, LocalDateTime creation_time, LocalDateTime lastmodified_time) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.total_price = total_price;
        this.status = status;
        this.userName= userName;
        this.userId = userId;
        this.creation_time = creation_time;
        this.lastmodified_time = lastmodified_time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
