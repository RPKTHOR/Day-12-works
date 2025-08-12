package com.example.productordersystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.productordersystem.entity.Order;
import com.example.productordersystem.entity.Product;
import com.example.productordersystem.repository.OrderRepository;
import com.example.productordersystem.repository.ProductRepository;
import com.example.productordersystem.service.OrderService;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepo;

    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testPlaceOrderSuccess() {
        Product p = new Product();
        p.setProductId(1L);
        p.setAvailableQuantity(10);
        when(productRepo.findById(1L)).thenReturn(Optional.of(p));
        when(orderRepo.save(any(Order.class))).thenAnswer(i -> i.getArgument(0));

        Order order = orderService.placeOrder(1L, 5);
        assertEquals(5, order.getQuantityOrdered());
    }

    @Test
    void testPlaceOrderFailure() {
        Product p = new Product();
        p.setProductId(1L);
        p.setAvailableQuantity(2);
        when(productRepo.findById(1L)).thenReturn(Optional.of(p));
        assertThrows(RuntimeException.class, () -> orderService.placeOrder(1L, 5));
    }
}
