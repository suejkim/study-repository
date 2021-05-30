package com.sjkim.xml_company;

public class WorkService {

    // 생성자 이용
    private final Worker worker;
    public WorkService(Worker worker) {
        this.worker = worker;
    }

    // setter method 이용
//    private Worker worker;
//    public void setWorker(Worker worker){
//        this.worker = worker;
//    }

    public void ask() {
        System.out.println(worker.getRole());
    }
}
