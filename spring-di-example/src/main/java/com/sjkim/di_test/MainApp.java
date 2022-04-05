package com.sjkim.di_test;

public class MainApp {
    public static void main(String[] args) {
        String url = "www.naver.com/books?search=java";
        Encoder encoder = new Base64Encoder();
        var base64Url = encoder.encode(url);
        Encoder urlEncoder = new UrlEncoder();
        var encodedUrl = urlEncoder.encode(url);
        System.out.println(base64Url);
        System.out.println(encodedUrl);
        // 여기까지 추상화
    }
}
