package com.sjkim.basic_company;

public class WorkService {

    private final Worker worker;

    public WorkService(){
        this.worker = new Boss(); // 의존성 지님. 인스턴스 직접 생성.
    }

    public void ask() {
        System.out.println(worker.getRole());
    }
}
