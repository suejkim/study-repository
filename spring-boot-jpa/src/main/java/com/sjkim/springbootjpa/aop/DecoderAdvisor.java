package com.sjkim.springbootjpa.aop;

import com.sjkim.springbootjpa.dto.UserInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Aspect
@Component
@Order(2)
public class DecoderAdvisor {

    @Pointcut(value = "@annotation(com.sjkim.springbootjpa.aop.Decoder)")
    private void decodePointcut() {
    }

    @Before(value = "decodePointcut()")
    public void before(JoinPoint joinPoint) throws IOException {
        var args = joinPoint.getArgs();
        for (var arg : args) {
            convertBase64(arg, (value) -> {
                return new String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8);
            });
        }
    }

    @AfterReturning(value = "decodePointcut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        if (returnObj instanceof UserInfo) {
            convertBase64(returnObj, (value) -> {
                return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
            });
        }
    }

    private void convertBase64(Object obj, Base64Convertor convertor) {
        var userInfo = (UserInfo) obj;
        var convertedName = convertor.convert(userInfo.getName());
        var convertedEmail = convertor.convert(userInfo.getEmail());
        userInfo.setName(convertedName);
        userInfo.setEmail(convertedEmail);
        System.out.println("========== convertBase64 : " +  userInfo);
    }

    interface Base64Convertor {
        String convert(String value);
    }
}
