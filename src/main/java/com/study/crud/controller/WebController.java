package com.study.crud.controller;

import com.study.crud.dto.DetailDTO;
import com.study.crud.dto.ListDTO;
import com.study.crud.dto.LoginDTO;
import com.study.crud.dto.UpdateDTO;
import com.study.crud.service.BoardServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class WebController {

    private final BoardServiceImpl boardService;

    @GetMapping("/")
    public String index(Model model) {
        List<ListDTO> posts = boardService.getAll();
        model.addAttribute("posts", posts);
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


    @GetMapping("/new")
    public String newPost() {
        return "new";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, @CookieValue(value = "id", required = false) String memberId) {
        DetailDTO post = boardService.getDetail(id, memberId);
        model.addAttribute("post", post);

        return "detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        UpdateDTO post = boardService.getUpdateDTO(id);
        model.addAttribute("post", post);
        return "update";
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "후이루이");
        model.addAttribute("img","image/후이루이.jpeg");
        return "hello";
    }

}
