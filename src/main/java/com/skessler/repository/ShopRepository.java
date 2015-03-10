package com.skessler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skessler.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
