package com.skessler.service;

import com.skessler.exception.ShopNotFound;
import com.skessler.model.Shop;

import java.util.List;

public interface ShopService {
	
	public Shop create(Shop shop);
	public Shop delete(int id) throws ShopNotFound;
	public List<Shop> findAll();
	public Shop update(Shop shop) throws ShopNotFound;
	public Shop findById(int id);

}