package com.example.boot07_restful.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {

    //Article 클래스
    private Long id;
    private String title;
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;
    //Member 클래스 필드
    private Long memberId;
    private String name;
    private String email;
    private Integer age;
}
