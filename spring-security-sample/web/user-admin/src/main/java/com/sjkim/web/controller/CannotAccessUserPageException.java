package com.sjkim.web.controller;

import org.springframework.security.access.AccessDeniedException;

public class CannotAccessUserPageException extends AccessDeniedException {
    public CannotAccessUserPageException() {
        super("접근할 수 없습니다.");
    }
}
