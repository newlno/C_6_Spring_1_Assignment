package com.sparta.SpringPrac.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogRequestDto {
    private String title;
    private String username;
    private long password;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime ModifiedAt;
}

