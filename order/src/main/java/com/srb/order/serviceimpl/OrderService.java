package com.srb.order.serviceimpl;

import com.srb.order.feign.ProductDetails;
import com.srb.order.feign.UserDetails;
import com.srb.order.kafka.OrderProducer;
import com.srb.order.model.*;
import com.srb.order.repository.OrderRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class OrderService implements com.srb.order.service.OrderService {



    private final OrderRepository orderRepository;

    private final ProductDetails productDetails;

    private final OrderProducer orderProducer;

    private final UserDetails userDetails;



    private final String secreteKey ="mysecretkeymysecretkeymysecretkeymysecretkey";

    private final Key key = Keys.hmacShaKeyFor(secreteKey.getBytes());


    public OrderService(OrderRepository orderRepository,
                        ProductDetails productDetails,
                        OrderProducer orderProducer,
                        UserDetails userDetails)
    {
        this.orderRepository = orderRepository;
        this.productDetails  = productDetails;
        this.orderProducer = orderProducer;
        this.userDetails = userDetails;
    }




    @Override
    public Order addOrder(OrderDto order, HttpServletRequest request) {

        String token = extractToken(request);
        // 2️⃣ Extract phone number from JWT
        String phNumber = extractPhNumber(token);

        ProductWrapper pw = productDetails.getProductById(order.getProductId()).getBody();

        UserDto userDto = userDetails.getUserDetails(phNumber);
        if(userDto==null)
        {
            System.out.println("User Retrun Null");
            return null;
        }

        if(pw==null)
        {
            System.out.println("Product Retrun Null");
            return null;
        }
        else {
            if(!pw.isActive())
            {
                return null;
            }
            if(pw.getQuantity() < order.getQuantity())
            {
                System.out.println("Out Of Stock");
                return null;
            }

            System.out.println(pw.getQuantity() + "  "+ order.getQuantity());

            Order saveOrder = new Order();

            saveOrder.setProductId(order.getProductId());
            double total_price = order.getQuantity()*pw.getPrice();
            saveOrder.setTotal_price(total_price);
            saveOrder.setQuantity(order.getQuantity());
            saveOrder.setStatus("CREATED");
            saveOrder.setUserId(userDto.getId());
            saveOrder.setUserName(userDto.getUserName());
            Order order1 = orderRepository.save(saveOrder);
            orderProducer.addOrderToKafka(order);

            addOrderForMail(saveOrder,userDto.getEmail(),pw.getName());

            if (order1 != null) {
                return order1;
            }
            return null;
        }
    }

    private String extractPhNumber(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    @Override
    public Order getOrder(Long id) {
        Order order1 = orderRepository.findById(id).get();
        if(order1 != null)
        {
            return order1;
        }
        return null;
    }

    public String extractToken(HttpServletRequest request)
    {
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer "))
        {
            return authHeader.substring(7);
        }

        return null;
    }


    public void addOrderForMail(Order order,String email,String pname)
    {
        OrderMailDto mail = new OrderMailDto();

        mail.setOrderId(order.getOrderId());
        mail.setEmail(email);
        mail.setStatus(order.getStatus());
        mail.setProductName(pname);
        mail.setTotalAmount(order.getTotal_price());

        try{
            orderProducer.addOrderToKafkaForMail(mail);
        }catch(Exception e)
        {
            System.out.println("Added to Kafka for mail "+e);
        }


    }




}
