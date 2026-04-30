package com.srb.ai_service.productconsumer;

import com.srb.ai_service.service.Ai_ServiceImpl;
import com.srb.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    private Ai_ServiceImpl aiService;

    public KafkaConsumer(Ai_ServiceImpl aiService)
    {
        this.aiService=aiService;
    }



    @KafkaListener(topics = "PRODUCT-AI-TOPIC", groupId = "AI-GROUP")
    public void consumeProductEvent(Product product) {
        logger.info("===== KAFKA MESSAGE RECEIVED =====");

        logger.info("Product ID       : {}", product.getId());
        logger.info("Name             : {}", product.getName());
        logger.info("Description      : {}", product.getDescription());
        logger.info("Price            : {}", product.getPrice());
        logger.info("Brand            : {}", product.getBrand());

        logger.info("adding product in vector DB for AI...");

        try {

            aiService.addListOfData(product);

            logger.info("Product added Successfully in AI");
        }catch (Exception e) {

        logger.error("Failed to process product {}", product.getId(), e);
        }

        logger.info("===== KAFKA MESSAGE PROCESSING END =====");
    }

}
