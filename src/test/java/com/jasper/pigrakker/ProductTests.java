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

        Product savedProduct = productRepository.save(product);

        assertNotNull(savedProduct.getId());
        assertEquals("Test Product", savedProduct.getProductname());
        assertEquals("Test Description", savedProduct.getDescription());

    }

}