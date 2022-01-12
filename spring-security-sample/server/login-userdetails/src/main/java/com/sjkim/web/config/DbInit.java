package com.sjkim.web.config;

import com.sjkim.user.domain.SpUser;
import com.sjkim.user.service.SpUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DbInit implements InitializingBean {

    private final SpUserService spUserService;

    @Override
    public void afterPropertiesSet() throws Exception {
        var email = "user@test.com";
        if(spUserService.findUser(email).isEmpty()) {
            var user = SpUser.builder()
                    .userId(1L).email(email)
                    .enabled(Boolean.TRUE).password("1111").build();
            spUserService.addAuthority(user, "ROLE_USER");
        }
    }
}
