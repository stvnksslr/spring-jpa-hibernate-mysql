package com.skessler.service;

        import java.util.List;

        import javax.annotation.Resource;

        import com.skessler.exception.OrderNotFound;
        import com.skessler.model.Acsorders;
        import com.skessler.repository.OrderRepository;
        import org.springframework.stereotype.Service;
        import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Override
    @Transactional
    public Acsorders create(Acsorders Acsorders) {
        com.skessler.model.Acsorders createdOrder = Acsorders;
        return orderRepository.save(createdOrder);
    }

    @Override
    @Transactional
    public Acsorders findById(int id) {
        return orderRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor=OrderNotFound.class)
    public Acsorders delete(int id) throws OrderNotFound {
        Acsorders deletedOrder = orderRepository.findOne(id);

        if (deletedOrder == null)
            throw new OrderNotFound();

        orderRepository.delete(deletedOrder);
        return deletedOrder;
    }

    @Override
    @Transactional
    public List<Acsorders> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor=OrderNotFound.class)
    public Acsorders update(Acsorders acsorders) throws OrderNotFound {
        Acsorders updatedOrder = orderRepository.findOne(acsorders.getId());

        if (updatedOrder == null)
            throw new OrderNotFound();

        updatedOrder.setOrderdata(acsorders.getOrderdata());
        updatedOrder.setProcessed(acsorders.getProcessed());
        return updatedOrder;
    }

}

