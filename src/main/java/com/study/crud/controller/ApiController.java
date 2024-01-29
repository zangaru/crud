package com.study.crud.controller;


import com.study.crud.domain.Member;
import com.study.crud.dto.LoginDTO;
import com.study.crud.dto.PostFormDTO;
import com.study.crud.dto.SignUpFormDTO;
import com.study.crud.dto.UpdateFormDTO;
import com.study.crud.service.interfaces.BoardService;
import com.study.crud.service.interfaces.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final MemberService memberService;
    private final BoardService boardService;

    @PostMapping(value = "/signup")
    public ResponseEntity<String> userSignup(@RequestBody SignUpFormDTO formDTO) {
        return memberService.signup(formDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        return memberService.login(loginDTO);
    }

    @PostMapping("/posts")
    public ResponseEntity<String> save(@RequestBody PostFormDTO postFormDTO) {
        return boardService.save(postFormDTO);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity remove(@PathVariable Long id) {
        ResponseEntity responseEntity = boardService.remove(id);
        return responseEntity;
    }

    @PostMapping("/posts/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdateFormDTO updateFormDTO) {
        ResponseEntity responseEntity = boardService.update(id, updateFormDTO);
        return responseEntity;
    }


    @GetMapping("/name")
    public String name() {
        return "푸바오";
    }
}
