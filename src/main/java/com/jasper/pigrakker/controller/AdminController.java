package com.jasper.pigrakker.controller;

import com.jasper.pigrakker.model.Packet;
import com.jasper.pigrakker.model.Product;
import com.jasper.pigrakker.repository.PacketRepository;
import com.jasper.pigrakker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PacketRepository packetRepository;
    @GetMapping("/product/create")
    public ModelAndView createProduct()
    {
        ModelAndView modelAndView = new ModelAndView("product/createProduct");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }
    @PostMapping("/product/create")
    public ModelAndView newProduct(@Validated Product product, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("alertMessage", "Er klopt iets niet :(");

            return new ModelAndView("product/createProduct");
        }
        if(productRepository.findByProductname(product.getProductname()).isPresent()) {
            modelAndView.addObject("alertMessage", "Dit product bestaat al!");
            return new ModelAndView("product/createProduct");
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
            modelAndView.setViewName("product/editProduct");
            return modelAndView;
        }
        product.setId(productid);
        productRepository.save(product);
        modelAndView.addObject("product", productRepository.findById(productid));
        modelAndView.setViewName("redirect:/admin/product");
        return modelAndView;
    }

    @GetMapping("/packet/{packetid}")
    public ModelAndView editPacket(@PathVariable Long packetid)
    {
        ModelAndView modelAndView = new ModelAndView("packet/editPacket");

        modelAndView.addObject("packet", packetRepository.findById(packetid));
        return modelAndView;
    }
    @PostMapping("/packet/{packetid}")
    public ModelAndView editPacket(@PathVariable Long packetid, @Validated Packet packet, BindingResult bindingResult)
    {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("alertMessage", "Er klopt iets niet :(");
            modelAndView.setViewName("packet/editPacket");
            return modelAndView;
        }
        packet.setId(packetid);
        packet.setPrice(round(packet.getPrice(),2));
        packetRepository.save(packet);
        modelAndView.addObject("product", productRepository.findById(packetid));
        modelAndView.setViewName("redirect:/admin/packet");
        return modelAndView;
    }

    @GetMapping("/packet/create")
    public ModelAndView createPacket()
    {
        ModelAndView modelAndView = new ModelAndView("packet/createPacket");
        modelAndView.addObject("packet", new Packet());
        return modelAndView;
    }
    @PostMapping("/packet/create")
    public ModelAndView newPacket(@Validated Packet packet, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("alertMessage", "Er klopt iets niet :(");

            return new ModelAndView("packet/createPacket");
        }
        if(packetRepository.findByPacketname(packet.getPacketname()).isPresent()) {
            modelAndView.addObject("alertMessage", "Dit product bestaat al!");
            return new ModelAndView("packet/createPacket");
        }
        packet.setPrice(round(packet.getPrice(),2));
        packetRepository.save(packet);
        modelAndView.addObject("alertMessage", "Succesvol geregistreed!");
        return new ModelAndView("redirect:/admin/packet");
    }
    public static Double round(Double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
