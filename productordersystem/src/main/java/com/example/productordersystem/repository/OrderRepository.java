package com.example.productordersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productordersystem.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
