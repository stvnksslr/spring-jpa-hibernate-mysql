package com.skessler.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skessler.model.Acsorders;

public interface OrderRepository extends JpaRepository<Acsorders, Integer> {

}