package com.sjkim.web.controller;

import com.sjkim.user.domain.SpUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class SessionController { // 세션 모니터링

    private final SessionRegistry sessionRegistry;

    @GetMapping("/sessions")
    public String sessions(Model model) {
        var sessionList = sessionRegistry.getAllPrincipals().stream()
                .map(p -> UserSession.builder()
                        .username(((SpUser) p).getUsername())
                        .sessions(sessionRegistry.getAllSessions(p, false).stream()
                                .map(s -> SessionInfo.builder()
                                        .sessionId(s.getSessionId())
                                        .time(s.getLastRequest())
                                        .build()).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
        model.addAttribute("sessionList", sessionList);
        return "/sessionList";
    }

    @PostMapping("/session/expire")
    public String expireSession(@RequestParam String sessionId) {
        var sessionInformation = sessionRegistry.getSessionInformation(sessionId);
        if (!sessionInformation.isExpired()) {
            sessionInformation.expireNow();
        }
        return "redirect:/sessions";
    }

    @GetMapping("/session-expired")
    public String sessionExpired() {
        return "/sessionExpired";
    }
}
