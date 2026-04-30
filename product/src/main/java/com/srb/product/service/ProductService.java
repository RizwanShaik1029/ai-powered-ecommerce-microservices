package com.srb.product.service;

import com.srb.product.model.Product;

import java.util.List;

public interface ProductService {

    String addProductSer(Product product);

    Product updateProductSer(Product product);

    Product getProductByIdSer(Long id);

    List<Product> getProductsSer();

    String deleteProductSer(Long id);

    List<Product> getProductsSerByIds(List<Long> ids);
}