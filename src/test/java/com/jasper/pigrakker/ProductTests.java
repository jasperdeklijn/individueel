package com.jasper.pigrakker;
import com.jasper.pigrakker.model.Product;
import com.jasper.pigrakker.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductTests {


    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    public void testSaveProduct() {
        Product product = new Product();
        product.setProductname("Test Product");
        product.setDescription("Test Description");
        product.setTotal(10);
        product.setPrice(29.99);
        product.setSold(0);

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getProductname());
        assertEquals("Test Description", savedProduct.getDescription());
        assertEquals(10, savedProduct.getTotal());
        assertEquals(29.99, savedProduct.getPrice(), 0.01); // Double comparison with tolerance
        assertEquals(0, savedProduct.getSold());
    }

    @Test
    @Transactional
    public void testProductnameNotBlank() {
        Product product = new Product();
        product.setDescription("Test Description");
        product.setTotal(10);
        product.setPrice(29.99);
        product.setSold(0);

        // Attempt to save a product with a blank productname
        assertThrows(ConstraintViolationException.class, () -> productRepository.save(product));
    }
}