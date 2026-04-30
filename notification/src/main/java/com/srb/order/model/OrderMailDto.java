package com.srb.order.model;

import java.io.Serializable;

public class OrderMailDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String productName;

    private Long orderId;

    private double totalAmount;

    private String status;

    private String email;

    public OrderMailDto(){}

    public OrderMailDto(String email, String status, double totalAmount, Long orderId, String productName) {
        this.email = email;
        this.status = status;
        this.totalAmount = totalAmount;
        this.orderId = orderId;
        this.productName = productName;

    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
