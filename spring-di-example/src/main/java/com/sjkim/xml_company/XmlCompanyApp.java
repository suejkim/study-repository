package com.sjkim.xml_company;

import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlCompanyApp {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext(
                "classpath:applicationContext.xml"
        );
        WorkService workService = context.getBean(WorkService.class);
        workService.ask();
        context.close();
        // new 연산자를 사용하지 않음!
    }
}
