package com.srb.ai_service.feignclient;

import com.srb.product.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductClient {

    @GetMapping("/product/get/ids")
    List<Product> getProductsByIds(@RequestParam List<Long> ids);
}
