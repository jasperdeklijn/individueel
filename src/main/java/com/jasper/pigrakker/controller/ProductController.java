package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.Product;
import com.jasper.pigrakker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/admin/product")
    public ModelAndView allProducts() {
        ModelAndView modelAndView = new ModelAndView("admin/products");
        modelAndView.addObject("products", productRepository.findAll());
        return modelAndView;
    }
    @GetMapping("/admin/product/create")
    public ModelAndView createProduct()
    {
        ModelAndView modelAndView = new ModelAndView("admin/createProduct");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/admin/product/create")
    public ModelAndView newProduct(@Validated Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("alertMessage", "Er klopt iets niet :(");

            return new ModelAndView("admin/createProduct");
        }
        if(productRepository.findByProductname(product.getProductname()).isPresent()) {
            modelAndView.addObject("alertMessage", "Dit product bestaat al!");
            return new ModelAndView("admin/createProduct");
        }
        product.setPrice(round(product.getPrice(), 2));
        product.setSold(0);
        productRepository.save(product);
        modelAndView.addObject("alertMessage", "Succesvol geregistreed!");
        return new ModelAndView("redirect:/admin/product");
    }

    @GetMapping("/admin/product/{productid}")
    public ModelAndView editProduct(@PathVariable Long productid)
    {
        ModelAndView modelAndView = new ModelAndView("product/editProduct");

        modelAndView.addObject("product", productRepository.findById(productid));
        return modelAndView;
    }
    @PostMapping("/admin/product/{productid}")
    public ModelAndView editProduct(@PathVariable Long productid, @Validated Product product, BindingResult bindingResult)
    {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("alertMessage", "Er klopt iets niet :(");
            modelAndView.setViewName("admin/createProduct");
            return modelAndView;
        }
        product.setPrice(round(product.getPrice(), 2));
        product.setSold(0);
        product.setId(productid);
        productRepository.save(product);
        modelAndView.addObject("product", productRepository.findById(productid));
        modelAndView.setViewName("redirect:/admin/product");
        return modelAndView;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    @GetMapping("/product/{productid}")
    public ModelAndView showProduct(@PathVariable Long productid)
    {
        Optional<Product> product = productRepository.findById(productid);
        if(product.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product met ID " + productid + " bestaat niet");
        }
        return new ModelAndView("product/showProduct").addObject("product", product.get());

    }
    public static Double round(Double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
