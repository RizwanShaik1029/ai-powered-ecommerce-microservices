package com.srb.product.serviceImpl;

import com.srb.product.kafka.ProductProducer;
import com.srb.product.model.OrderDto;
import com.srb.product.model.Product;
import com.srb.product.repository.ProductRepo;
import com.srb.product.service.ProductService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSerImpl implements ProductService {

    private final ProductRepo productRepo;

    private final ProductProducer productProducer;


    public ProductSerImpl(ProductRepo productRepo, ProductProducer productProducer) {
        this.productRepo = productRepo;
        this.productProducer=productProducer;
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    @Override
    public String addProductSer(Product product) {
        try {
            productRepo.save(product);
            productProducer.addProductToKafka(product);
            return "Product Added Successfully";
        } catch (Exception e) {
            throw new RuntimeException("Product creation failed", e);
        }
    }

    @CachePut(value = "product", key = "#product.id")
    @Override
    public Product updateProductSer(Product product) {

        Optional<Product> existingProduct = productRepo.findById(product.getId());

        if (existingProduct.isEmpty()) {
            return null;
        }

        return productRepo.save(product);
    }

    @Cacheable(value = "product", key = "#id")
    @Override
    public Product getProductByIdSer(Long id) {

        Optional<Product> product = productRepo.findById(id);

        return product.orElse(null);
    }

    @Cacheable(value = "products")
    @Override
    public List<Product> getProductsSer() {

        return productRepo.findAll();
    }

    @CacheEvict(value = {"product", "products"}, key = "#id", allEntries = true)
    @Override
    public String deleteProductSer(Long id) {

        Optional<Product> product = productRepo.findById(id);

        if (product.isEmpty()) {
            return "Product Not Found";
        }

        productRepo.deleteById(id);

        return "Product Deleted Successfully";
    }

    @Override
    public List<Product> getProductsSerByIds(List<Long> ids) {
        return productRepo.findAllById(ids);
    }

    @CachePut(value = "product", key = "#order.productId")
    public Product updateProductSerForKafka(OrderDto order) {

        try {
            if (order.getProductId() != null) {
                Optional<Product> productOpt = productRepo.findById(order.getProductId());

                if (productOpt.isEmpty()) {
                    System.out.println("Product not found");
                    return null;
                }

                Product p = productOpt.get();

                int quantityProduct = p.getQuantity() - order.getQuantity();

                System.out.println(quantityProduct + " quantity in consumer");

                p.setQuantity(quantityProduct);

                return productRepo.save(p);

            }
            return null;
        }catch(Exception e){

                System.out.println("Exception Occurs at updating the product from kafka " + e);

                return null;
            }

    }
}