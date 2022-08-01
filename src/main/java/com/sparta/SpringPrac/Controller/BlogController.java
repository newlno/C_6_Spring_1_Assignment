package com.sparta.SpringPrac.Controller;

import com.sparta.SpringPrac.dto.*;
import com.sparta.SpringPrac.model.*;
import com.sparta.SpringPrac.response.BasicResponse;
import com.sparta.SpringPrac.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  // json 전달 짝꿍
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // 3번
    @PostMapping("/api/blogs")  // 생성
    public Blog createMemo(@RequestBody BlogRequestDto requestDto) {
        Blog blog = blogService.createMemo(requestDto);
        return blog;
    }

    // 2번
    @GetMapping("/api/blogs")  // 전체조회
    public BasicResponse findAll() {
        BasicResponse blog = blogService.findAll();
        return blog;
    }

    //  4번
    @GetMapping("/api/blogs/{id}") // 일부 조회
    public ResponseEntity<? extends BasicResponse> select(@PathVariable Long id) {
        ResponseEntity<? extends BasicResponse> blog = blogService.select(id);
        return blog;
    }

    // 5번
    @PostMapping("/api/blogs/{id}")  //비밀번호 확인
    public ResponseEntity<? extends BasicResponse> check(@PathVariable Long id, @RequestBody BlogPasswordDto requestDto) {
       ResponseEntity<? extends BasicResponse> blog = blogService.checkPassword(id,requestDto);
       return blog;
    }

    // 6번
    @PutMapping("/api/blogs/{id}")  // 수정
    public ResponseEntity<? extends BasicResponse> update(@PathVariable Long id,@RequestBody BlogUpdataDto requestDto) {
        ResponseEntity<? extends BasicResponse> blog = blogService.update(id,requestDto);
        return blog;
    }

    // 7번
    @DeleteMapping("/api/blogs/{id}")  // 삭제
    public ResponseEntity<? extends BasicResponse> deleteBlog(@PathVariable Long id,@RequestBody BlogPasswordDto requestDto) {
        ResponseEntity<? extends BasicResponse> blog = blogService.deleteBlog(id,requestDto);
        return blog;
    }
}
