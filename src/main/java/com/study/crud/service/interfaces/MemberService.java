package com.study.crud.service.interfaces;

import com.study.crud.dto.LoginDTO;
import com.study.crud.dto.SignUpFormDTO;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity<String> signup(SignUpFormDTO formDTO);
    ResponseEntity<String> login(LoginDTO loginDTO);
}
