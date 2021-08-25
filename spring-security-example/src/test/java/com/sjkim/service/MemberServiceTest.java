package com.sjkim.service;

import com.sjkim.BaseTest;
import com.sjkim.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MemberServiceTest extends BaseTest {

    @Autowired
    private MemberService memberService;

    @Test
    void getMemberByLoginId() {
        String loginId = "admin01";
        Member member = memberService.getMemberByLoginId(loginId);
        Assertions.assertEquals(member.getLoginId(), loginId);
    }
}