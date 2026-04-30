package com.srb.product.kafka;

import com.srb.product.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {

    public final KafkaTemplate<String, Product> kafkaTemplate;

    public  final Logger logger = LoggerFactory.getLogger(ProductProducer.class);

    @Value("${kafka.topic.product-ai}")
    private String topic;

    public ProductProducer(KafkaTemplate<String, Product> kafkaTemplate)
    {
        this.kafkaTemplate=kafkaTemplate;
    }

    public void addProductToKafka(Product product)
    {
        kafkaTemplate.send(topic,product)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        logger.info("Message sent successfully to Kafka For Ai");
                        logger.info("Topic: {}", result.getRecordMetadata().topic());
                        logger.info("Partition: {}", result.getRecordMetadata().partition());
                        logger.info("Offset: {}", result.getRecordMetadata().offset());
                        logger.info("Payload: {}", product);
                    } else {
                        logger.info("Kafka send failed For Ai: " + ex.getMessage());
                    }
                });
    }

}
