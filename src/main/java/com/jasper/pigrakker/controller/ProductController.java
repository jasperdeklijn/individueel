package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.Product;
import com.jasper.pigrakker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;


@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public ModelAndView allProducts() {
        ModelAndView modelAndView = new ModelAndView("admin/products");
        modelAndView.addObject("products", productRepository.findAll());
        return modelAndView;
    }
    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

}
