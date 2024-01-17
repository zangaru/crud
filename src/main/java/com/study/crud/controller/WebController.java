package com.study.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }


    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "후이루이");
        model.addAttribute("img","image/후이루이.jpeg");
        return "hello";
    }

}
