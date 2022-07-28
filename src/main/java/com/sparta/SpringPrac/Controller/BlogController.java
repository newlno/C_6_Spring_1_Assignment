package com.sparta.SpringPrac.Controller;

import com.sparta.SpringPrac.domain.*;
import com.sparta.SpringPrac.domain.dto.*;
import com.sparta.SpringPrac.service.response.BasicResponse;
import com.sparta.SpringPrac.service.BlogService;
import com.sparta.SpringPrac.service.response.CommonResponse;
import com.sparta.SpringPrac.service.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor  // 파이널 짝꿍
@RestController  // json 전달 짝꿍
public class BlogController {

    private final BlogRepository blogRepository;
    private final BlogService blogService;

    // 3번
    @PostMapping("/api/blogs")  // 생성
    public Blog createMemo
    (@RequestBody BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }

    // 2번
    @GetMapping("/api/blogs")  // 전체조회
    public BasicResponse findAll() {
        List<BlogAllDto> blogs = blogRepository.findAllByOrderByCreatedAtDesc();
        if (blogs.isEmpty()) {
            return new ErrorResponse("작성된 글이 없습니다.");
        }
        return new CommonResponse<>(blogs);
    }


    //  4번
    @GetMapping("/api/blogs/{id}") // 일부 조회
    public ResponseEntity<? extends BasicResponse> select(@PathVariable("id") Long id) {
        List<BlogSearchDto> blogs = blogRepository.findBlogById(id);
        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 ID가 없습니다."));
        }
        return ResponseEntity.ok().body(new CommonResponse<>(blogs));
    }


    // 5번
    @PostMapping("/api/blogs/{id}")  //비밀번호 확인
    public ResponseEntity<? extends BasicResponse> check(@PathVariable("id") Long id, @RequestBody BlogPasswordDto requestDto) {
        List<BlogSearchDto> blogs = blogRepository.findBlogById(id);
        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 ID가 없습니다."));
        }
        Blog blog = blogRepository.findById(id).get();
        if (blog.getPassword().equals(requestDto.getPassword())) {
            return ResponseEntity.ok().body(new CommonResponse<>("true"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("비밀번호가 일치하지 않습니다."));
        }
    }

    // 6번
    @PutMapping("/api/blogs/{id}")  // 수정
    public ResponseEntity<? extends BasicResponse> update(@PathVariable Long id,@RequestBody BlogUpdataDto requestDto) {
        List<BlogSearchDto> blogs = blogRepository.findBlogById(id);
        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 ID가 없습니다."));
        }
        Blog blog = blogRepository.findById(id).get();
        if (blog.getPassword() != requestDto.getPassword()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("비밀번호가 다릅니다."));
        } else {
            blogService.update(id, requestDto);
            return ResponseEntity.ok().body(new CommonResponse<>(blog));
        }
    }

    // 7번
    @DeleteMapping("/api/blogs/{id}")  // 삭제
    public ResponseEntity<? extends BasicResponse> deleteBlog(@PathVariable Long id,@RequestBody BlogPasswordDto requestDto) {
        List<BlogSearchDto> blogs = blogRepository.findBlogById(id);
        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 ID가 없습니다."));
        }
        Blog blog = blogRepository.findById(id).get();
        if (!Objects.equals(blog.getPassword(), requestDto.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("비밀번호가 다릅니다."));
        } else {
            blogRepository.deleteById(id);
            return ResponseEntity.ok().body(new CommonResponse<>(id+"번 글을 삭제했습니다."));
        }
    }
}
