package com.sjkim.config_company;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigCompanyApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(BeanConfig.class);
        context.refresh(); // config 외부에서 추가시 꼭 필요 -> 재정립느낌
        WorkService workService1 = context.getBean("workService", WorkService.class);
        WorkService workService2 = context.getBean("workService", WorkService.class);
        System.out.println(workService1.hashCode());
        System.out.println(workService2.hashCode());
        if(workService1.equals(workService2)) {
            System.out.println("같은 객체임");
        }
        workService1.ask();
        context.close();
    }
}
