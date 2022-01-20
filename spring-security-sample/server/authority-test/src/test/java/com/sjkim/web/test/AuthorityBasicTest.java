package com.sjkim.web.test;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;

class AuthorityBasicTest extends WebIntegrationTest {

    private TestRestTemplate client;

    @Test
    void callHello() {
        client = new TestRestTemplate("user1", "1111");
        var response = client.getForEntity(uri("/hello"), String.class);
        System.out.println(response.getBody());
        assertThat(response.getBody(), Matchers.equalTo("hello"));
    }

    @Test
    void failToCallHelloUnauthorized() {
        client = new TestRestTemplate();
        var response = client.getForEntity(uri("/hello"), String.class);
        System.out.println(response.getBody());
        assertThat(HttpStatus.UNAUTHORIZED, Matchers.equalTo(response.getStatusCode()));
    }

    @Test
    void failToCallHelloForbidden() {
        client = new TestRestTemplate("admin1", "1111");
        var response = client.getForEntity(uri("/hello"), String.class);
        System.out.println(response.getBody());
        assertThat(HttpStatus.FORBIDDEN, Matchers.equalTo(response.getStatusCode()));
    }
}
