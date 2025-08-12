package com.example.productordersystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productordersystem.entity.Product;
import com.example.productordersystem.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	public Product addProduct(Product p) {
		return productRepo.save(p);
	}
	
	public List<Product> getAllproducts(){
		return productRepo.findAll();
	}
	
	public void updateStock(Long productId, int qty) {
		Product p = productRepo.findById(productId).orElseThrow();
		productRepo.save(p);
	}
}