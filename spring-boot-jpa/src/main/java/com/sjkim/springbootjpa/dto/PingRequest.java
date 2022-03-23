package com.sjkim.springbootjpa.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PingRequest {
    private String message;
    private LocalDateTime timeStamp;

    @Builder
    public PingRequest(String message) {
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}
