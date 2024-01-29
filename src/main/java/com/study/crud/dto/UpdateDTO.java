package com.study.crud.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateDTO {
    private long id;
    private String title;
    private String content;
}
