package com.sjkim.di_test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Encoder implements Encoder {
    @Override
    public String encode(String value) {
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }
}
