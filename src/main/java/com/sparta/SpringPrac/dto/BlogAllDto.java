package com.sparta.SpringPrac.dto;

import com.sparta.SpringPrac.model.Blog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class BlogAllDto {
    private String title;
    private String username;
    private LocalDate createdAt;

    public BlogAllDto(Blog Entity) {
        this.username = Entity.getUsername();
        this.title = Entity.getTitle();
        this.createdAt = LocalDate.from(Entity.getCreatedAt());
    }

}
