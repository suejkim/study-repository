package com.sjkim.springbootexample.service.di_constructor;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class AService {

    //    @Lazy // 여기 선언하면 DService에 적용되지 않음. 생성자 인수에서 적용해야함
    private final DService dService;

    public AService(@Lazy DService dService) {
        System.out.println("AService constructor");
        this.dService = dService;
    }
}

/*
생성자를 이용한 주입 Constructor Injection
DService -> AService -> CService -> BService 순으로 로드된다.
*/

/*
@Lazy Spring 시작하면서 이루어지는 Bean 등록 시점이 아닌 실제 사용시점에 Bean이 만들어진다.
AService 생성자 인수 DService에 적용시
AService -> CService -> DService -> BService 순으로 로드된다.
 */