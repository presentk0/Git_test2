package com.example.boot07_restful.controller;

import com.example.boot07_restful.dto.MemberResponseDto;
import com.example.boot07_restful.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final MemberService memberService;

// thmleaf 이용
//    @GetMapping("/members")
//    public String getMembers(Model model) {
//        model.addAttribute("members", memberService.findAll());
//        return "members";
//    }

//    @GetMapping("/example/members")
//    public ResponseEntity<String> getExample() {
//        return new ResponseEntity<>("Member Found", HttpStatus.OK);
//    }

    @GetMapping("/example/members/{id}")
    public ResponseEntity<MemberResponseDto> getExample(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findById(id);
        if(memberResponseDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                        .body(memberResponseDto);
    }

    @GetMapping("/api/example/members")
    @ResponseBody
    public List<MemberResponseDto> getExampleMembers() {
        return memberService.findAll();
    }


}
