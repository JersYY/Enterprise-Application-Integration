package com.example.productapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.productapi.dto.ProductRequest;
import com.example.productapi.dto.ProductResponse;
import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetAllProductsSuccess() {
        Product product1 = new Product(1L, "Laptop", 15000000.0, 5);
        Product product2 = new Product(2L, "Mouse", 150000.0, 20);
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<ProductResponse> responses = productService.getAllProducts();

        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getName()).isEqualTo("Laptop");
        assertThat(responses.get(1).getName()).isEqualTo("Mouse");
        verify(productRepository).findAll();
    }

    @Test
    void testCreateProductSuccess() {
        ProductRequest request = new ProductRequest("Keyboard", 500000.0, 15);
        Product savedProduct = new Product(1L, "Keyboard", 500000.0, 15);
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductResponse response = productService.createProduct(request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getName()).isEqualTo("Keyboard");
        assertThat(response.getPrice()).isEqualTo(500000.0);
        assertThat(response.getStock()).isEqualTo(15);
        verify(productRepository).save(any(Product.class));
    }
}
