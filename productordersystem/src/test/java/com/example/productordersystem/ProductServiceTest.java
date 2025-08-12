package com.example.productordersystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.productordersystem.entity.Product;
import com.example.productordersystem.repository.ProductRepository;
import com.example.productordersystem.service.ProductService;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepo;

    @InjectMocks
    private ProductService productService;

    @Test
    void testAddProduct() {
        Product p = new Product();
        p.setName("Test");
        when(productRepo.save(p)).thenReturn(p);
        assertEquals("Test", productService.addProduct(p).getName());
    }

    @Test
    void testUpdateStock() {
        Product p = new Product();
        p.setProductId(1L);
        p.setAvailableQuantity(10);
        when(productRepo.findById(1L)).thenReturn(Optional.of(p));
        productService.updateStock(1L, 20);
        verify(productRepo).save(p);
    }
}
