package com.example.boot07_restful;

import com.example.boot07_restful.domain.Article;
import com.example.boot07_restful.domain.Member;
import com.example.boot07_restful.repository.ArticleRepository;
import com.example.boot07_restful.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitRunner implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception{
        if(memberRepository.count()==0) {
            var members = List.of(
                    Member.builder().name("홍길동").email("123@naver.com").age(25).build(),
                    Member.builder().name("김길동").email("456@naver.com").age(26).build(),
                    Member.builder().name("김길동").email("789@naver.com").age(27).build()
            );
            memberRepository.saveAll(members);
        }

    }
}
