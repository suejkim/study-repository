package com.sjkim.springbootexample.persistence.member;

import com.sjkim.springbootexample.domain.Member;
import com.sjkim.springbootexample.domain.Profile;
import net.andreinc.mockneat.types.enums.PassStrengthType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static net.andreinc.mockneat.unit.user.Emails.emails;
import static net.andreinc.mockneat.unit.user.Names.names;
import static net.andreinc.mockneat.unit.user.Passwords.passwords;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProfileRepository profileRepository;

    @Test
    void addMemberAndProfile() {
        for (int i = 1; i <= 100; i++) {
            String email = emails().get();
            String password = passwords().type(PassStrengthType.MEDIUM).get();
            String name = names().last().get();
            var member = Member.builder()
                    .loginId(email)
                    .password(password)
                    .username(name).build();

            var profile = Profile.builder()
                    .name("test_" + i + ".txt")
                    .current(true)
                    .member(member)
                    .build();

            // @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
            // CascadeType.ALL로 설정하여 한번에 저장할 수 있음
            profileRepository.save(profile);
        }
    }

    @Test
    @Transactional
    void getMemberListFromProfile() {
        var profiles = profileRepository.findAll();

        // @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        // FetchType.LAZY 및 @Transactional 설정 필요
//        profiles.stream().map(Profile::getMember).forEach(
//                member -> {
//                    System.out.println(member.getLoginId());
//                }
//        );
        assertThat(profiles.size()).isEqualTo(profileRepository.count());
    }
}
