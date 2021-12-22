package com.sjkim;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sjkim.student.Student;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MultiChainProxyApplicationTest {

    @LocalServerPort
    int port;

//    @Autowired
//    private ObjectMapper objectMapper;


    RestTemplate client = new RestTemplate();

    @BeforeEach
    void setUp() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        client.setMessageConverters(messageConverters);
    }

    @Test
    @DisplayName("인증성공")
    @SneakyThrows
    void successAuthentication() {
//        var url = String.format("http://localhost:%d/api/teacher/students", port);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("CHOI:1111".getBytes(StandardCharsets.UTF_8)));
//        var entity = new HttpEntity<>(null, headers);
//        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, entity, String.class);
//        System.out.println(">>>>>>>>>"+ response);
//        var list = new ObjectMapper().readValue(response.getBody(), ArrayList.class);
//        System.out.println(list);
//        assertEquals(3, list.size());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + Base64.getEncoder().encodeToString("CHOI:1111".getBytes(StandardCharsets.UTF_8)));


        var entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<Student>> resp = client.exchange("http://localhost:" + port + "/api/teacher/students",
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<Student>>() {
                });

        assertNotNull(resp.getBody());
        assertEquals(3, resp.getBody().size());
    }
}