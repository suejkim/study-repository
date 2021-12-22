package com.sjkim;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // TODO check
class BasicAuthenticationTest {

    @LocalServerPort
    int port;

    RestTemplate client = new RestTemplate();

    private String homeUrl() {
        return "http://localhost:" + port + "/home";
    }

    @Test
    @DisplayName("인증실패")
    void failAuthentication() {
        HttpClientErrorException exception = assertThrows(HttpClientErrorException.class, () -> {
                    var response = client.getForObject(homeUrl(), String.class);
                    System.out.println(response);
                }
        );
        assertEquals(401, exception.getRawStatusCode());
    }

    @Test
    @DisplayName("인증성공")
    void successAuthentication() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("user1:1111".getBytes(StandardCharsets.UTF_8)));
        var entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = client.exchange(homeUrl(), HttpMethod.GET, entity, String.class);
        assertEquals("Hello", response.getBody());
    }

    @Test
    @DisplayName("인증성공. TestRestTemplate 이용")
    void successAuthenticationWithTestRestTemplate() {
        TestRestTemplate testRestTemplate = new TestRestTemplate("user1", "1111"); // Basic Token 지원
        var response = testRestTemplate.getForObject(homeUrl(), String.class);
        assertEquals("Hello", response);
    }

    @Test
    @DisplayName("POST METHOD. 인증성공. TestRestTemplate 이용")
    void successAuthenticationRequestPost() {
        TestRestTemplate testRestTemplate = new TestRestTemplate("user1", "1111"); // Basic Token 지원
        var response = testRestTemplate.postForEntity(homeUrl(), "KIM", String.class);
        assertEquals("HelloKIM", response.getBody());
    }
}
