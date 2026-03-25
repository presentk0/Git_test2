package com.example.boot07_restful.service;

import com.example.boot07_restful.domain.Article;
import com.example.boot07_restful.domain.Member;
import com.example.boot07_restful.dto.ArticleRequest;
import com.example.boot07_restful.dto.ArticleResponse;
import com.example.boot07_restful.exception.NotFoundException;
import com.example.boot07_restful.repository.ArticleRepository;
import com.example.boot07_restful.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    //기사 작성 메서드
    public ArticleResponse create(Long memberId, ArticleRequest articleRequest) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundException::new);
        Article article = Article.builder()
                .title(articleRequest.getTitle())
                .description(articleRequest.getDescription())
                .member(member)
                .build();
        articleRepository.save(article);
        return mapToArticleResponse(article);
    }

    public List<ArticleResponse> findAll() {
        return articleRepository.findAll()
                .stream()
                .map(this::mapToArticleResponse)
                .toList();
    }

    //특정 멤버가 작성한 게시글 목록 반환
    public List<ArticleResponse> findByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NotFoundException::new);
        return articleRepository.findAllByMember(member)
                .stream()
                .map(this::mapToArticleResponse)
                .toList();
    }

    //게시슬 id를 통해 특정 게시글을 조회하는 메서드
    public ArticleResponse findById(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapToArticleResponse(article);
    }

    //게시글 수정 메서드
    public ArticleResponse update(Long id, ArticleRequest articleRequest) {
        Article article = articleRepository.findById(id).orElseThrow(NotFoundException::new);
        article.setTitle(articleRequest.getTitle());
        article.setDescription(articleRequest.getDescription());
        articleRepository.save(article);
        return mapToArticleResponse(article);
    }

    //게시글 삭제 메서드
    public void delete(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(NotFoundException::new);
        articleRepository.delete(article);
    }

    //Entity->Response 변환 메서드
    private ArticleResponse mapToArticleResponse(Article article) {
        return ArticleResponse.builder()
                .id(article.getId())
                .title(article.getTitle())
                .description(article.getDescription())
                .created(article.getCreated())
                .updated(article.getUpdated())
                .memberId(article.getMember().getId())
                .name(article.getMember().getName())
                .email(article.getMember().getEmail())
                .build();
    }

}
