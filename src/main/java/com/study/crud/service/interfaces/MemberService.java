package com.study.crud.service.interfaces;

import com.study.crud.dto.SignUpFormDTO;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity signup(SignUpFormDTO formDTO);
}
