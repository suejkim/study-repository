package com.sjkim.springbootjpa.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAop {

    @Pointcut("execution(* com.sjkim.springbootjpa.service.PingService*.*(..))")
    private void testPointcut() {

    }

    @Before(value = "testPointcut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        var method = methodSignature.getMethod();
        System.out.println("TestAop ========== method :" + method);
        var args = joinPoint.getArgs();
        for (var arg : args) {
            System.out.println("TestAop ========== arg:" + arg);
            System.out.println("TestAop ========== arg className: " + arg.getClass().getSimpleName());
        }
    }

    @AfterReturning(value = "testPointcut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("TestAop ========== returnObj: " + returnObj);
    }
}
