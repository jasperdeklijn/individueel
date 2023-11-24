package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.Product;
import com.jasper.pigrakker.model.User;
import com.jasper.pigrakker.repository.ProductRepository;
import com.jasper.pigrakker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/home")
    public ModelAndView adminHome() {
        return new ModelAndView("/admin/home.html");
    }

    @GetMapping("/product/create")
    public ModelAndView createProduct()
    {
        ModelAndView modelAndView = new ModelAndView("admin/createProduct");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/product/create")
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
        productRepository.save(product);
        modelAndView.addObject("alertMessage", "Succesvol geregistreed!");
        return new ModelAndView("redirect:/admin/product");
    }

    @GetMapping("/product/{productid}")
    public ModelAndView editProduct(@PathVariable Long productid)
    {
        ModelAndView modelAndView = new ModelAndView("product/editProduct");

        modelAndView.addObject("product", productRepository.findById(productid));
        return modelAndView;
    }
    @PostMapping("/product/{productid}")
    public ModelAndView editProduct(@PathVariable Long productid, @Validated Product product, BindingResult bindingResult)
    {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("alertMessage", "Er klopt iets niet :(");
            modelAndView.setViewName("admin/createProduct");
            return modelAndView;
        }
        product.setId(productid);
        productRepository.save(product);
        modelAndView.addObject("product", productRepository.findById(productid));
        modelAndView.setViewName("redirect:/admin/product");
        return modelAndView;
    }

}
