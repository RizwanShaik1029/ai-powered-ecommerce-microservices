package com.srb.order.kafka;

import com.srb.order.model.OrderDto;
import com.srb.order.model.OrderMailDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    private final KafkaTemplate<String, OrderMailDto> kafkaTemplate2;

    public OrderProducer(KafkaTemplate<String, OrderDto> kafkaTemplate,KafkaTemplate<String, OrderMailDto> kafkaTemplate2)
    {
        this.kafkaTemplate=kafkaTemplate;
        this.kafkaTemplate2=kafkaTemplate2;

    }
    public void addOrderToKafka(OrderDto orderDto)
    {
        System.out.println("Kafka Started producer");

        kafkaTemplate.send("ORDER-PRODUCT-TOPIC", orderDto)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Message sent successfully");
                        System.out.println("Topic: " + result.getRecordMetadata().topic());
                        System.out.println("Partition: " + result.getRecordMetadata().partition());
                        System.out.println("Offset: " + result.getRecordMetadata().offset());
                    } else {
                        System.out.println("Kafka send failed: " + ex.getMessage());
                    }
                });
    }

    public void addOrderToKafkaForMail(OrderMailDto orderMailDto)
    {
        System.out.println("Kafka Started producer for mail");

        kafkaTemplate2.send("ORDER-MAIL-TOPIC", orderMailDto)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Mail message sent successfully");
                        System.out.println("Topic: " + result.getRecordMetadata().topic());
                        System.out.println("Partition: " + result.getRecordMetadata().partition());
                        System.out.println("Offset: " + result.getRecordMetadata().offset());
                    } else {
                        System.out.println("Kafka mail send failed: " + ex.getMessage());
                    }
                });
    }

}
