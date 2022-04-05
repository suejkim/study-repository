package com.sjkim.di_test;

import java.net.URLEncoder;
import java.nio.charset.Charset;

public class UrlEncoder implements IEncoder {
    @Override
    public String encode(String value) {
        return URLEncoder.encode(value, Charset.defaultCharset());
    }
}
