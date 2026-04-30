package com.srb.order.controller;

import com.srb.order.model.Order;
import com.srb.order.model.OrderDto;
import com.srb.order.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService)
    {
        this.orderService=orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestBody OrderDto order, HttpServletRequest request)
    {
        try {
            Order saveOrder = orderService.addOrder(order,request);

            if (saveOrder != null) {
                return new ResponseEntity<>(saveOrder, HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id)
    {
        try {
            Order saveOrder = orderService.getOrder(id);

            if (saveOrder != null) {
                return new ResponseEntity<>(saveOrder, HttpStatus.FOUND);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
