package com.study.crud.service;

import com.study.crud.domain.Member;
import com.study.crud.domain.MemberRole;
import com.study.crud.dto.LoginDTO;
import com.study.crud.dto.SignUpFormDTO;
import com.study.crud.repository.MemberRepository;
import com.study.crud.service.interfaces.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public ResponseEntity<String> signup(SignUpFormDTO formDTO) {

        Optional<Member> member = memberRepository.findById(formDTO.getId());

        if(member.isEmpty()) {
            Member newMember = Member.builder()
                    .id(formDTO.getId())
                    .password(formDTO.getPassword())
                    .name(formDTO.getName())
                    .role(MemberRole.USER)
                    .build();

            memberRepository.save(newMember);

            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("fail", HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> login(LoginDTO loginDTO) {

        Optional<Member> member = memberRepository.findById(loginDTO.getId());
        Member memberEntity = member.orElse(null);

        if (memberEntity==null) {
            return new ResponseEntity<>("해당 아이디를 가진 회원이 존재하지 않습니다.", HttpStatus.OK);
        }

        if (memberEntity.getPassword().equals(loginDTO.getPassword())) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("비밀번호가 일치하지 않습니다.", HttpStatus.OK);
        }

    }
}
