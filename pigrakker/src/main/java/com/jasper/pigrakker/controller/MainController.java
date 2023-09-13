package com.jasper.pigrakker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/showViewPage")
    public String passParametersWithModel(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("spring", "mvc");
        model.addAttribute("message", "Baeldung");
        model.mergeAttributes(map);
        return "view/viewPage";
    }
    @GetMapping("/printViewPage")
    public String passParametersWithModelMap(ModelMap map) {
        map.addAttribute("welcomeMessage", "welcome");
        map.addAttribute("message", "Baeldung");
        return "view/viewPage";
    }
    @GetMapping("/goToViewPage")
    public ModelAndView passParametersWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("view/viewPage");
        modelAndView.addObject("message", "Baeldung");
        return modelAndView;
    }

}
