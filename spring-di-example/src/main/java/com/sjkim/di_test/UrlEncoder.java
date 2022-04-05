package com.sjkim.di_test;

import java.net.URLEncoder;
import java.nio.charset.Charset;

public class UrlEncoder implements Encoder {
    @Override
    public String encode(String value) {
        return URLEncoder.encode(value, Charset.defaultCharset());
    }
}
