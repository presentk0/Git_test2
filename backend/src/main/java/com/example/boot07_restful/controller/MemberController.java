package com.example.boot07_restful.controller;

import com.example.boot07_restful.dto.ArticleRequest;
import com.example.boot07_restful.dto.ArticleResponse;
import com.example.boot07_restful.dto.MemberRequestDto;
import com.example.boot07_restful.dto.MemberResponseDto;
import com.example.boot07_restful.service.ArticleService;
import com.example.boot07_restful.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ArticleService articleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto post(@RequestBody MemberRequestDto memberRequestDto) {
        return memberService.create(memberRequestDto);
    }

    @GetMapping
    public List<MemberResponseDto> getAll() {
        return memberService.findAll();
    }

    @GetMapping("/{id}")
    public MemberResponseDto get(@PathVariable("id") Long id) {
        return memberService.findById(id);
    }

    @PutMapping("/{id}")
    public MemberResponseDto put(@PathVariable("id") Long id, @RequestBody MemberRequestDto memberRequestDto) {
        return memberService.update(id, memberRequestDto);
    }

    @PatchMapping("/{id}")
    public MemberResponseDto patch(@PathVariable("id") Long id, @RequestBody MemberRequestDto memberRequestDto) {
        return memberService.patch(id, memberRequestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        memberService.deleteById(id);
    }

    @PostMapping("/{id}/articles")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleResponse postArticle(@PathVariable("id") Long id, @RequestBody ArticleRequest articleRequest) {
        return articleService.create(id, articleRequest);
    }

    //@GetMapping("/{id}/articles")
//    public void getArticle(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        request.getSession().getServletContext().getRequestDispatcher("/api/articles?memberId="+id).forward(request,response);
//    }

}
