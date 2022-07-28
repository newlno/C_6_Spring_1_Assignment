package com.sparta.SpringPrac.domain;

import com.sparta.SpringPrac.domain.dto.BlogRequestDto;
import com.sparta.SpringPrac.domain.dto.BlogUpdataDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자 만들어줌
@Getter //
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Blog extends Timestamped { //상속받기 / 생성,수정타임자동확인해줌
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id // 고유값 / 번호
    private Long id;

    @Column(nullable = false)
    private String username; //작성자명


    @Column(nullable = false)
    private String title; // 제목



    @Column(nullable = false)
    private String contents; // 내용

    @Column(nullable = false)
    private Long password; // 비밀번호


    public Blog(String username, String title) {
        this.username = username;
        this.title = title;
    }

    public Blog(BlogRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void update(BlogUpdataDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.modifiedAt = requestDto.getModifiedAt();
    }
}