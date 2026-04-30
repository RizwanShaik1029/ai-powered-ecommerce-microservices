package com.srb.order.feign;

import com.srb.order.model.ProductWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("PRODUCT-SERVICE")
public interface ProductDetails {

    @GetMapping("/product/get/{id}")
    public ResponseEntity<ProductWrapper> getProductById(@PathVariable Long id);


}
