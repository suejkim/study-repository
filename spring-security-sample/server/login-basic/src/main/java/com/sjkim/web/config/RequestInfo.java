package com.sjkim.web.config;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestInfo {
    private String remoteIP;
    private String sessionId;
    private LocalDateTime loginTime;
}
