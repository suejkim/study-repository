package com.sjkim.service;

import com.sjkim.model.Member;
import com.sjkim.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId).orElse(null);
    }
}
