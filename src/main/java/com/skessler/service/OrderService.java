package com.skessler.service;

import java.util.List;

import com.skessler.exception.OrderNotFound;
import com.skessler.model.Acsorders;

public interface OrderService {

    public Acsorders create(Acsorders acsorders);
    public Acsorders delete(int id) throws OrderNotFound;
    public List<Acsorders> findAll();
    public Acsorders update(Acsorders acsorders) throws OrderNotFound;
    public Acsorders findById(int id);

}