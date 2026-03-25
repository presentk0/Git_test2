package com.example.boot07_restful.service;

import com.example.boot07_restful.domain.Member;
import com.example.boot07_restful.dto.MemberRequestDto;
import com.example.boot07_restful.dto.MemberResponseDto;
import com.example.boot07_restful.exception.NotFoundException;
import com.example.boot07_restful.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //멤버 생성 메서드 RequestDto->Entity->DB 저장-> ReponseEntity 변환
    public MemberResponseDto create(MemberRequestDto memberRequest) {
        Member member = Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .age(memberRequest.getAge())
                .enable(true)
                .build();
        memberRepository.save(member);
        return mapToMemberResponse(member);
    }

    //멤버 조회 메서드
    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        return mapToMemberResponse(member);
    }

    //전체 멤버 조회 메서드
    public List<MemberResponseDto> findAll() {
        return memberRepository
                .findAll()
                .stream()
                .map(this::mapToMemberResponse)
                .toList();
    }

    //멤버 정보 수정 메서드
    public MemberResponseDto update(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);

        member.setName(memberRequestDto.getName());
        member.setEmail(memberRequestDto.getEmail());
        member.setAge(memberRequestDto.getAge());

        memberRepository.save(member);
        return mapToMemberResponse(member);
    }

    public MemberResponseDto patch(Long id, MemberRequestDto memberRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        //전달된 값이 있는 필드만 업데이트
        if(memberRequestDto.getName() != null) member.setName(memberRequestDto.getName());
        if(memberRequestDto.getEmail() != null) member.setEmail(memberRequestDto.getEmail());
        if(memberRequestDto.getAge() != null) member.setAge(memberRequestDto.getAge());

        memberRepository.save(member);
        return mapToMemberResponse(member);
    }

    //삭제 메서드
    public void deleteById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(NotFoundException::new);
        memberRepository.delete(member);
    }

    //Member Entity-> MemberResponseDto 변환
    private MemberResponseDto mapToMemberResponse(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .age(member.getAge())
                .build();
    }
}
