package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.Product;
import com.jasper.pigrakker.model.User;
import com.jasper.pigrakker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/product")
@Secured("ROLE_ADMIN")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("")
    public ModelAndView allProducts() {
        ModelAndView modelAndView = new ModelAndView("admin/products");
        modelAndView.addObject("products", productRepository.findAll());
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView createProduct()
    {
        ModelAndView modelAndView = new ModelAndView("admin/createProduct");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView newProduct(@Validated Product product, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("alertMessage", "Er klopt iets niet :(");

            return new ModelAndView("admin/createProduct", model);
        }
        if(productRepository.findByProductname(product.getProductname()).isPresent()) {
            model.addAttribute("alertMessage", "Dit product bestaat al!");
            return new ModelAndView("admin/createProduct", model);
        }
        product.setPrice(round(product.getPrice(), 2));
        product.setSold(0);
        productRepository.save(product);
        model.addAttribute("alertMessage", "Succesvol geregistreed!");
        return new ModelAndView("redirect:/admin/products",model);
    }
    public static Double round(Double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
