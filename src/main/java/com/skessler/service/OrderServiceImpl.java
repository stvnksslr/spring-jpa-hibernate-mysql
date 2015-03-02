package com.skessler.service;

import com.skessler.exception.OrderNotFound;
import com.skessler.model.Order;
import com.skessler.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public Order create(Order order) {
        Order createdOrder = order;
        return orderRepository.save(createdOrder);
    }

    @Override
    @Transactional
    public Order findById(int id) {
        return orderRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor=OrderNotFound.class)
    public Order delete(int id) throws OrderNotFound {
        Order deletedOrder = orderRepository.findOne(id);

        if (deletedOrder == null)
            throw new OrderNotFound();

        orderRepository.delete(deletedOrder);
        return deletedOrder;
    }

    @Override
    @Transactional
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor=OrderNotFound.class)
    public Order update(Order order) throws OrderNotFound {
        Order updatedOrder = orderRepository.findOne(order.getId());

        if (updatedOrder == null)
            throw new OrderNotFound();

        updatedOrder.setOrderdata(order.getOrderdata());
        return updatedOrder;
    }

}
