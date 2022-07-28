package com.sparta.SpringPrac.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BlogUpdataDto {
    private Long id;
    private String title;
    private String username;
    private long password;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}