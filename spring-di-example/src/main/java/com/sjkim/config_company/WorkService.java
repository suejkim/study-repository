package com.sjkim.config_company;

public class WorkService {
    private Worker worker;
    // setter method
    public void setWorkManager(Worker worker) {
        this.worker = worker;
    }

    public void ask() {
        System.out.println(worker.getRole());
    }
}
