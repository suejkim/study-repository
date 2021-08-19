package security;

import com.sjkim.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/dispatcher-servlet.xml",
        "file:src/main/webapp/WEB-INF/spring-security.xml"
})
@WebAppConfiguration
@Slf4j
class MemberTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void updateBCryptPasswordEncoder() {
        var memberList = memberRepository.findAll();
        memberList.forEach(
                member -> member.applyPassword(bCryptPasswordEncoder.encode(member.getPassword()))
        );
        memberRepository.saveAll(memberList);
    }
}

