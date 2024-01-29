package com.study.crud.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ListDTO {
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;
    private int userViews;
    private int adminViews;
    private String memberName;
}
