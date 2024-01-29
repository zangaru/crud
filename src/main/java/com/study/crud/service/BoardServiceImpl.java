package com.study.crud.service;

import com.study.crud.domain.Board;
import com.study.crud.domain.Member;
import com.study.crud.domain.MemberRole;
import com.study.crud.dto.*;
import com.study.crud.repository.BoardRepository;
import com.study.crud.repository.MemberRepository;
import com.study.crud.service.interfaces.BoardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    /* 글 등록 */
    @Override
    public ResponseEntity<String> save(PostFormDTO formDTO) {

        Optional<Member> member = memberRepository.findById(formDTO.getMemberId());
        if(member.isPresent()) {
            Member memberEntity = member.get();

            Board post = Board.builder()
                    .title(formDTO.getTitle())
                    .content(formDTO.getContent())
                    .member(memberEntity)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .adminViews(0)
                    .userViews(0)
                    .likes(0)
                    .build();

            boardRepository.save(post);

            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }

    /* 글 목록 */
    @Override
    public List<ListDTO> getAll() {

        List<Board> posts = boardRepository.findAll();
        List<ListDTO> list = new ArrayList<>();

        for (Board post : posts) {
            Member member = post.getMember();

            ListDTO dto = ListDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .createdAt(post.getCreatedAt())
                    .userViews(post.getUserViews())
                    .adminViews(post.getAdminViews())
                    .memberName(member.getName())
                    .build();

            list.add(dto);
        }

        return list;
    }

    /* 글 상세 */
    @Override
    public DetailDTO getDetail(Long id, String memberId) {
        Optional<Board> board = boardRepository.findById(id);
        Board boardEntity = board.orElse(null);

        Member member = boardEntity.getMember();

        //로그인 여부 체크
        boolean isLoggedIn = memberId != null && !memberId.isEmpty();

        if(!isLoggedIn || !memberId.equals(member.getId())) {//쿠키의 멤버 아이디(로그인한 아이디)가 포스트 작성자 아이디와 다를 경우
            if(member.getRole().equals(MemberRole.ADMIN)) {//Role이 관리자이면
                boardEntity.countAdmin();//adminViews 1 증가
            } else {
                boardEntity.countUser();//일반회원이면 userViews 1 증가
            }
        }

        DetailDTO detailDTO = DetailDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .createdAt(boardEntity.getCreatedAt())
                .updatedAt(boardEntity.getUpdatedAt())
                .userViews(boardEntity.getUserViews())
                .adminViews(boardEntity.getAdminViews())
                .memberName(member.getName())
                .memberId(member.getId())
                .build();

        return detailDTO;
    }

    /* 글 삭제 */
    @Override
    public ResponseEntity remove(Long id) {

        boardRepository.deleteById(id);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    /* 수정 글 가져오기 */
    @Override
    public UpdateDTO getUpdateDTO(Long id) {
        Optional<Board> board = boardRepository.findById(id);
        Board boardEntity = board.orElse(null);

        UpdateDTO updateDTO = UpdateDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .build();


        return updateDTO;
    }

    /* 글 수정 */
    @Override
    public ResponseEntity update(Long id, UpdateFormDTO updateFormDTO) {

        Optional<Board> board = boardRepository.findById(id);
        Board boardEntity = board.orElse(null);

        boardEntity.update(updateFormDTO);

        return new ResponseEntity("success", HttpStatus.OK);
    }

}
