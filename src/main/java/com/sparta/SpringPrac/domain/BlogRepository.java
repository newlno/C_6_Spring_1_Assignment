package com.sparta.SpringPrac.domain;

import com.sparta.SpringPrac.domain.dto.BlogAllDto;
import com.sparta.SpringPrac.domain.dto.BlogSearchDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<BlogAllDto> findAllByOrderByCreatedAtDesc();
    List<BlogSearchDto> findBlogById(Long id);

}
