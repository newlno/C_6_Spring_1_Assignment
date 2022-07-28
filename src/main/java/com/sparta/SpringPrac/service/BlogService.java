package com.sparta.SpringPrac.service;

import com.sparta.SpringPrac.domain.Blog;
import com.sparta.SpringPrac.domain.BlogRepository;
import com.sparta.SpringPrac.domain.dto.BlogUpdataDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service  // 서비스라는거 알려줌
public class BlogService {

    // final: 서비스에게 꼭 필요한 녀석임을 명시
    private final BlogRepository blogRepository;

//    // 생성자를 통해, Service 클래스를 만들 때 꼭 Repository를 넣어주도록
//    // 스프링에게 알려줌

    // 수정기능
    @Transactional // SQL 쿼리가 일어나야 함을 스프링에게 알려줌
    public Long update(Long id, BlogUpdataDto requestDto) {
        Blog blogs = blogRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        blogs.update(requestDto);
        return blogs.getId();
    }
}
