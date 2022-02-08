package com.sjkim.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Base64;
import java.util.Map;

class JWTSimpleTest {

    @Test
    @DisplayName("jjwt 를 이용한 토큰 테스트")
    void jjwtTest() {
        String jjwt = Jwts.builder().addClaims(Map.of("name", "KIM", "age", 20))
                .signWith(SignatureAlgorithm.HS256, "secretKey").compact();
        System.out.println(jjwt);
        printToken(jjwt);
    }


    @Test
    @DisplayName("java-jwt 를 이용한 토큰 테스트")
    void javaJwtTest() {
        String jwt = JWT.create().withClaim("name", "KIM").withClaim("age", 20).sign(Algorithm.HMAC256("secretKey"));
        System.out.println(jwt);
        printToken(jwt);
    }

    private void printToken(String token) {
        String[] tokens = token.split("\\.");
        System.out.println("header: " + new String(Base64.getDecoder().decode(tokens[0])));
        System.out.println("body: " + new String(Base64.getDecoder().decode(tokens[1])));
    }
}
