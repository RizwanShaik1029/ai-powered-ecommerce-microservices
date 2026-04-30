package com.srb.product.controller;

import com.srb.product.model.Product;
import com.srb.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {

        String result = productService.addProductSer(product);

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {

        Product updatedProduct = productService.updateProductSer(product);

        if (updatedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Product product = productService.getProductByIdSer(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Product>> getProducts() {

        List<Product> products = productService.getProductsSer();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {

        String result = productService.deleteProductSer(id);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get/ids")
    public ResponseEntity<List<Product>> getProductsByIds(@RequestParam List<Long> ids) {

        List<Product> products = productService.getProductsSerByIds(ids);

        return ResponseEntity.ok(products);
    }

}