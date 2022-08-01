package com.sparta.SpringPrac.service;

import com.sparta.SpringPrac.dto.*;
import com.sparta.SpringPrac.model.Blog;
import com.sparta.SpringPrac.repository.BlogRepository;
import com.sparta.SpringPrac.response.BasicResponse;
import com.sparta.SpringPrac.response.CommonResponse;
import com.sparta.SpringPrac.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service  // 서비스라는거 알려줌
public class BlogService {

    // final: 서비스에게 꼭 필요한 녀석임을 명시
    private final BlogRepository blogRepository;
    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    //글생성
    public Blog createMemo(@RequestBody BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        return blogRepository.save(blog);
    }

    //전체조회
    public BasicResponse findAll() {
        List<BlogAllDto> blogs = blogRepository.findAllByOrderByCreatedAtDesc();
        if (blogs.isEmpty()) {
            return new ErrorResponse("작성된 글이 없습니다.");
        }
        return new CommonResponse<>(blogs);
    }

    //조건조회
    public ResponseEntity<? extends BasicResponse> select(@PathVariable("id") Long id) {
        List<BlogSearchDto> blogs = blogRepository.findBlogById(id);
        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 ID가 없습니다."));
        }
        return ResponseEntity.ok().body(new CommonResponse<>(blogs));
    }

    //비밀번호 확인
    public ResponseEntity<? extends BasicResponse> checkPassword(@PathVariable("id") Long id, @RequestBody BlogPasswordDto requestDto) {
        Blog blog = blogRepository.findById(id).get();
        if (blog == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 ID가 없습니다."));
        } else if (requestDto.getPassword().equals(blog.getPassword())) {
            return ResponseEntity.ok().body(new CommonResponse<>("true"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("비밀번호가 일치하지 않습니다."));
        }
    }

    //수정
    public ResponseEntity<? extends BasicResponse> update(@PathVariable Long id,@RequestBody BlogUpdataDto requestDto) {
        Blog blogs = blogRepository.findById(id).get();
        if (blogs == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 ID가 없습니다."));
        } else if (blogs.getPassword().equals(requestDto.getPassword())) {
            blogs.update(requestDto);
            blogRepository.save(blogs);
            return ResponseEntity.ok().body(new CommonResponse<>(blogs));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("비밀번호가 다릅니다."));
        }
    }

    //삭제
    public ResponseEntity<? extends BasicResponse> deleteBlog(@PathVariable Long id,@RequestBody BlogPasswordDto requestDto) {
        Blog blogs = blogRepository.findById(id).get();
        if (blogs == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("일치하는 ID가 없습니다."));
        } else if (blogs.getPassword().equals(requestDto.getPassword())) {
            blogRepository.deleteById(id);
            return ResponseEntity.ok().body(new CommonResponse<>(id + "번 글을 삭제했습니다."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("비밀번호가 다릅니다."));
        }
    }
}
