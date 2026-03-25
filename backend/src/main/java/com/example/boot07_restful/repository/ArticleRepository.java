package com.example.boot07_restful.repository;

import com.example.boot07_restful.domain.Article;
import com.example.boot07_restful.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByMember(Member member);
}
