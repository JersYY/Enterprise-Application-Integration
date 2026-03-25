package com.example.productapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class ProductTest {

    @Test
    void testProductCreation() {
        Product product = new Product(1L, "Laptop", 15000000.0, 10);

        assertEquals(1L, product.getId());
        assertEquals("Laptop", product.getName());
        assertEquals(15000000.0, product.getPrice());
        assertEquals(10, product.getStock());
    }

    @Test
    void testNoArgsConstructor() {
        Product product = new Product();

        assertNotNull(product);
    }
}
