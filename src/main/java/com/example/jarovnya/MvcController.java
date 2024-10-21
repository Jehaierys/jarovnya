package com.example.jarovnya;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

    @GetMapping("/home")
    public String home() {
        return "main";
    }
    @GetMapping("/catalog")
    public String catalog() {
        return "catalog";
    }
}
