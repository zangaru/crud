package com.study.crud.service.interfaces;

import com.study.crud.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {
    ResponseEntity<String> save(PostFormDTO formDTO);
    List<ListDTO> getAll();
    DetailDTO getDetail(Long id, String memberId);
    ResponseEntity remove(Long id);
    UpdateDTO getUpdateDTO(Long id);
    ResponseEntity update(Long id, UpdateFormDTO updateFormDTO);
}
