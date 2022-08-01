package com.sparta.SpringPrac.dto;

import com.sparta.SpringPrac.model.Blog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BlogSearchDto {
    private String title;
    private String username;
    private LocalDate createdAt;
    private String contents;

    public BlogSearchDto(Blog Entity) {
        this.title = Entity.getTitle();
        this.username = Entity.getUsername();
        this.createdAt = LocalDate.from(Entity.getCreatedAt());
        this.contents = Entity.getContents();
    }
}
