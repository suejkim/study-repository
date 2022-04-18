package com.sjkim.springbootjpa.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Aspect
@Component
public class TestAdvisor {

    @Pointcut("execution(* com.sjkim.springbootjpa.service.SomethingService.*(..))")
    private void testPointcut() {
    }

    @Before(value = "testPointcut()")
    public void before(JoinPoint joinPoint) throws IOException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("====== Before Advice ============");
        var method = methodSignature.getMethod();
        System.out.println("method : " + method);

        for (var param : methodSignature.getParameterNames()) {
            System.out.println("methodSignature paramName : " + param);
        }

        for (var param : method.getParameters()) {
            System.out.println("method paramName : " + param.getName());
            System.out.println("method paramType : " + param.getType());
        }

        var args = joinPoint.getArgs();
        for (var arg : args) {
            System.out.println("joinPoint arg : " + arg);
            System.out.println("joinPoint arg className : " + arg.getClass().getSimpleName());
        }
    }

    @After(value = "testPointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("====== After Advice ============");
        System.out.println("예외가 발생하든 하지 않든 반드시 실행됨");
    }

    @AfterReturning(value = "testPointcut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        System.out.println("====== After Returning Advice ============");
        System.out.println("예외가 발생하면 실행되지 않음");
        System.out.println("returnObj: " + returnObj);
    }

    @AfterThrowing(value = "testPointcut()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        System.out.println("====== After Throwing Advice ============");
        System.out.println("예외가 발생하면 실행됨");

        System.out.println("throwable class: " + throwable.getClass());
        System.out.println("throwable message: " + throwable.getMessage());
        System.out.println("throwable cause: " + throwable.getCause());
    }
}
