package com.srb.product.model;

public class OrderDto {

    private Long Id;
    private Long productId;
    private int quantity;

    public OrderDto() {}

    public OrderDto(Long Id, Long productId, int quantity) {
        this.Id = Id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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
}
