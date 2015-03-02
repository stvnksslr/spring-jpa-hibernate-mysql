package com.skessler.service;

import com.skessler.exception.OrderNotFound;
import com.skessler.model.Order;

import java.util.List;

public interface OrderService {

    public Order create(Order order);
    public Order delete(int id) throws OrderNotFound;
    public List<Order> findAll();
    public Order update(Order order) throws OrderNotFound;
    public Order findById(int id);

}
