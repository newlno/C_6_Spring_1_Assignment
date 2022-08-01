package com.sparta.SpringPrac.repository;

import com.sparta.SpringPrac.dto.BlogAllDto;
import com.sparta.SpringPrac.dto.BlogSearchDto;
import com.sparta.SpringPrac.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<BlogAllDto> findAllByOrderByCreatedAtDesc();
    List<BlogSearchDto> findBlogById(Long id);

}
