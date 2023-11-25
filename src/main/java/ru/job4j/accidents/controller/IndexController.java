package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("user", "Stepan Zazyan");
        return "index";
    }
}
