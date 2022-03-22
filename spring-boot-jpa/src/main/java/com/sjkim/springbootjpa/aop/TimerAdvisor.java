package com.sjkim.springbootjpa.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class TimerAdvisor {

    @Pointcut(value = "@annotation(com.sjkim.springbootjpa.aop.Timer)")
    private void timerPointcut() {

    }

    @Around("timerPointcut()")
    public Object timerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        var result = joinPoint.proceed();

        stopWatch.stop();
        System.out.println("timer: " + stopWatch.getTotalTimeSeconds());

        return result; // Object를 Return 하지 않으면 @Timer가 적용된 메서드에서는 null을 반환함.
    }
}
