package com.example.productordersystem.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.productordersystem.entity.Order;
import com.example.productordersystem.entity.Product;
import com.example.productordersystem.repository.OrderRepository;
import com.example.productordersystem.repository.ProductRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private ProductRepository productRepo;
	
	public Order placeOrder(Long productId, int quantity) {
		Product product = productRepo.findById(productId).orElseThrow();
        if (product.getAvailableQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }
        product.setAvailableQuantity(product.getAvailableQuantity() - quantity);
        productRepo.save(product);
        
        Order order = new Order();
        order.setProduct(product);
        order.setOrderDate(LocalDate.now());
        order.setQuantityOrdered(quantity);
        return orderRepo.save(order);
	}

	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}
}
