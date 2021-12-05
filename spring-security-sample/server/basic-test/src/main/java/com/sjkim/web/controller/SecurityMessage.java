package com.sjkim.web.controller;

import lombok.*;
import org.springframework.security.core.Authentication;

@Getter
@ToString
@Data
@Builder
public class SecurityMessage {
    private Authentication authentication;
    private String message;
}
