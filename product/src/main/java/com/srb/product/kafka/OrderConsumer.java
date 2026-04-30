package com.srb.product.kafka;

import com.srb.product.model.OrderDto;
import com.srb.product.model.Product;
import com.srb.product.serviceImpl.ProductSerImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final ProductSerImpl productSer;

    public OrderConsumer(ProductSerImpl productSer)
    {
        this.productSer = productSer;
    }

    //@KafkaListener(topics = "ORDER-CREATED-TOPIC", groupId = "PRODUCT-GROUP")
//    public void getOrder(OrderDto order)
//    {
//        System.out.println(" topic consumer start : ");
//        Product p = productSer.updateProductSerForKafka(order);
//
//        System.out.println(p.getQuantity()+" "+ p.getName());
//
//    }

    @KafkaListener(topics = "ORDER-PRODUCT-TOPIC", groupId = "PRODUCT-GROUP")
    public void getOrder(OrderDto order)
    {
        System.out.println("===== KAFKA MESSAGE RECEIVED =====");

        System.out.println("Product ID  : " + order.getProductId());
        System.out.println("Quantity    : " + order.getQuantity());

        System.out.println("Updating product quantity in DB...");

        Product p = productSer.updateProductSerForKafka(order);

        System.out.println("Product Updated Successfully");
        if(p != null) {
            System.out.println("Product Name : " + p.getName());
            System.out.println("Remaining Qty: " + p.getQuantity());
        }
        System.out.println("===== KAFKA MESSAGE PROCESSING END =====");
    }

}
