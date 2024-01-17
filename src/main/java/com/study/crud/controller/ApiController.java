package com.study.crud.controller;


import com.study.crud.dto.LoginDTO;
import com.study.crud.dto.SignUpFormDTO;
import com.study.crud.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final MemberService memberService;

    @PostMapping(value = "/signup")
    public ResponseEntity<String> userSignup(@RequestBody SignUpFormDTO formDTO) {
        return memberService.signup(formDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return memberService.login(loginDTO);
    }

    @GetMapping("/name")
    public String name() {
        return "푸바오";
    }
}
