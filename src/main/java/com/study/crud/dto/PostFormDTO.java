package com.study.crud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostFormDTO {
    private String title;
    private String content;
    private String memberId;
}
