package com.sjkim.springbootjpa.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;


@Aspect
@Component
public class TimerAdvisor {

    @Pointcut(value = "@annotation(com.sjkim.springbootjpa.aop.Timer)")
    private void timerPointcut() {
    }

    @Around("timerPointcut()")
    public Object timerAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("====== Around Advice ============");
        var stopWatch = new StopWatch();
        stopWatch.start();
        var result = pjp.proceed();
        stopWatch.stop();
        System.out.println("==============> [Timer] " + stopWatch.getTotalTimeSeconds());

        // Annotation Timer에서 element 가져오기
        var methodSignature = (MethodSignature) pjp.getSignature();
        var method = methodSignature.getMethod();
        var annotation = method.getAnnotation(Timer.class);
        System.out.println(annotation.message());

        return result; // Object를 Return 하지 않으면 @Timer가 적용된 메서드에서는 null을 반환함.
    }
}
