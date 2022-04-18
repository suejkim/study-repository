package com.sjkim.springbootjpa.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SomethingRequest {
    private String message;
    private LocalDateTime timeStamp;

    @Builder
    public SomethingRequest(String message) {
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
