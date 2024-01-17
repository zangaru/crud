package com.study.crud.controller;


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

    @PostMapping("/signup")
    public ResponseEntity userSignup(@RequestBody SignUpFormDTO formDTO) {
        return memberService.signup(formDTO);
    }

    @GetMapping("/name")
    public String name() {
        return "푸바오";
    }
}
