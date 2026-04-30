package com.srb.order.service;

import com.srb.order.model.Order;
import com.srb.order.model.OrderDto;
import jakarta.servlet.http.HttpServletRequest;

public interface OrderService {
    Order addOrder(OrderDto order, HttpServletRequest request);

    Order getOrder(Long id);
}
