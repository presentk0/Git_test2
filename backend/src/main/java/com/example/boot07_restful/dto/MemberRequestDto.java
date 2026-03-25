package com.example.boot07_restful.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRequestDto {

    private String name;
    private String email;
    private Integer age;
    private String password;
    private Boolean enable;
}
