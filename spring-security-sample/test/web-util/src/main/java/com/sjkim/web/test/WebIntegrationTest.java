package com.sjkim.web.test;


import lombok.SneakyThrows;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URI;
import java.net.URISyntaxException;

import static java.lang.String.format;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebIntegrationTest {

    @LocalServerPort
    int port;

    @SneakyThrows
    public URI uri(String path) {
        return new URI(format("http://localhost:%d%s", port, path));
    }

}
