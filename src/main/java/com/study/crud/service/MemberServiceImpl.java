package com.study.crud.service;

import com.study.crud.domain.Member;
import com.study.crud.domain.MemberRole;
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
    public ResponseEntity signup(SignUpFormDTO formDTO) {

        Optional<Member> member = memberRepository.findById(formDTO.getId());

        if(member.isEmpty()) {
            Member newMember = Member.builder()
                    .id(formDTO.getId())
                    .password(formDTO.getPassword())
                    .name(formDTO.getName())
                    .role(MemberRole.USER)
                    .build();

            memberRepository.save(newMember);

            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("fail", HttpStatus.OK);
        }
    }
}
