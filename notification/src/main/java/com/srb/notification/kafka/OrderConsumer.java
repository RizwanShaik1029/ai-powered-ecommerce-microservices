package com.srb.notification.kafka;

import com.srb.notification.service.SendMail;
import com.srb.notification.model.OrderMailDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {


    private SendMail sendMail;

    public OrderConsumer(SendMail sendMail)
    {
        this.sendMail = sendMail;
    }

    @KafkaListener(topics = "ORDER-MAIL-TOPIC", groupId = "NOTIFICATION-GROUP")
    public void getOrder(OrderMailDto mailDto)
    {
        System.out.println(" topic consumer start : ");
        sendMail.sendMailToUser(mailDto);

    }

}
