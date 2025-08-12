package com.example.productordersystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productordersystem.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
