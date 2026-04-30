package com.srb.notification.service;

import com.srb.notification.model.OrderMailDto;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMail {

    private final JavaMailSender mailSender;

    public SendMail(JavaMailSender mailSender)
    {
        this.mailSender=mailSender;
    }

    public void sendMailToUser(OrderMailDto mailDto)
    {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(mailDto.getEmail());

        message.setText(
                "Hello,\n\n" +
                        "Your order has been created successfully.\n\n" +
                        "Order ID: " + mailDto.getOrderId() + "\n" +
                        "Product: " + mailDto.getProductName() + "\n" +
                        "Amount: " + mailDto.getTotalAmount() + "\n" +
                        "Status: " + mailDto.getStatus()
        );
        mailSender.send(message);

        System.out.println("mail sent ");
    }

}
