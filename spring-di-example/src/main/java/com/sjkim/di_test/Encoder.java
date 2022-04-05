package com.sjkim.di_test;

public class Encoder {

    private IEncoder iEncoder;

    public Encoder() {
//        this.iEncoder = new UrlEncoder();
//        this.iEncoder = new Base64Encoder(); // 테스트 및 배포할 때마다 클래스 내부를 수정해야함. 비효율
    }

    public String encode(String value) {
        return iEncoder.encode(value);
    }
}
