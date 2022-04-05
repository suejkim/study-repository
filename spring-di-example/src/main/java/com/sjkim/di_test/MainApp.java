package com.sjkim.di_test;

public class MainApp {
    public static void main(String[] args) {
        String url = "www.naver.com/books?search=java";
//        IEncoder encoder = new Base64Encoder();
//        var base64Url = encoder.encode(url);
//        IEncoder urlEncoder = new UrlEncoder();
//        var encodedUrl = urlEncoder.encode(url);
//        System.out.println(base64Url);
//        System.out.println(encodedUrl);
        // 여기까지 추상화

        Encoder encoder = new Encoder();
        var result = encoder.encode(url);
        System.out.println(result);

    }
}
