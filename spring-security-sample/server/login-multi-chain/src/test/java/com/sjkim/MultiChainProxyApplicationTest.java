package com.sjkim;

import com.sjkim.student.Student;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MultiChainProxyApplicationTest {

    @LocalServerPort
    int port;

    TestRestTemplate testClient = new TestRestTemplate("CHOI", "1");
    RestTemplate client = new RestTemplate();

    @Test
    @DisplayName("인증성공1")
    @SneakyThrows
    void successAuthentication1() {
        ResponseEntity<List<Student>> resp = testClient.exchange("http://localhost:" + port + "/api/teacher/students",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                });

        assertNotNull(resp.getBody());
        assertEquals(3, resp.getBody().size());
    }


    @Test
    @DisplayName("인증성공2")
    @SneakyThrows
    void successAuthentication2() {
        var url = String.format("http://localhost:%d/api/teacher/students", port);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("CHOI:1111".getBytes(StandardCharsets.UTF_8)));
        var entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> resp = client.exchange(url, HttpMethod.GET, entity, String.class);
        assertNotNull(resp.getBody());
    }
}