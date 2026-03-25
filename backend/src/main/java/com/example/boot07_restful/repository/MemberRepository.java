package com.example.boot07_restful.repository;

import com.example.boot07_restful.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
