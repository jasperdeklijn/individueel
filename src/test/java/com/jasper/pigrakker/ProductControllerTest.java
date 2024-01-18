package com.jasper.pigrakker;

import com.jasper.pigrakker.controller.ProductController;
import com.jasper.pigrakker.model.Product;
import com.jasper.pigrakker.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getProductsTest() throws Exception {
        // Mock data
        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("2000");
        product1.setProductname("Product 1");
        product1.setTotalKG(1.5);
        Product product2 = new Product();
        product2.setId(2);
        product2.setDescription("1000");
        product2.setProductname("Product 2");
        product2.setTotalKG(1.5);
        List<Product> products = Arrays.asList(product1, product2);

        // Mock behavior
        when(productRepository.findAll()).thenReturn(products);

        // Test GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", is(product1.getDescription())))
                .andExpect(jsonPath("$[0].productname", is(product1.getProductname())))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].description", is(product2.getDescription())))
                .andExpect(jsonPath("$[1].productname", is(product2.getProductname())));
    }
}
