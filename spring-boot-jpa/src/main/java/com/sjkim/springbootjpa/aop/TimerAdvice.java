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
public class TimerAdvice {

    @Pointcut("execution(* com.sjkim.springbootjpa.controller.PingController*.*(..))")
    private void timerPointcut() {

    }

    @Before(value = "timerPointcut()")
    public void timerBefore(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        var method = methodSignature.getMethod();
        System.out.println(method);
        var args = joinPoint.getArgs();
        for (var arg : args) {
            System.out.println(arg);
            System.out.println(arg.getClass().getSimpleName());
        }
    }

    @AfterReturning(value = "timerPointcut()", returning = "returnObj")
    public void timerAfterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println(returnObj);
    }
}
