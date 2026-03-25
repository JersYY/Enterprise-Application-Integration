package com.example.productapi.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.productapi.model.Product;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testSaveAndFindProduct() {
        Product product = new Product();
        product.setName("Smartphone");
        product.setPrice(5000000.0);
        product.setStock(20);

        Product savedProduct = productRepository.save(product);
        Optional<Product> found = productRepository.findById(savedProduct.getId());

        assertThat(savedProduct.getId()).isNotNull();
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Smartphone");
    }

    @Test
    void testFindAllProducts() {
        entityManager.persist(new Product(null, "Laptop", 15000000.0, 5));
        entityManager.persist(new Product(null, "Mouse", 150000.0, 50));
        entityManager.flush();

        List<Product> products = productRepository.findAll();

        assertThat(products).hasSize(2);
    }

    @Test
    void testDeleteProduct() {
        Product product = entityManager.persist(new Product(null, "Keyboard", 500000.0, 15));
        entityManager.flush();

        productRepository.deleteById(product.getId());
        Optional<Product> found = productRepository.findById(product.getId());

        assertThat(found).isEmpty();
    }
}
