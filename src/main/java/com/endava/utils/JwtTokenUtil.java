package com.endava.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import static com.endava.utils.Constants.EXPIRE_DURATION;
import static com.endava.utils.Constants.SECRET_KEY;

public class JwtTokenUtil implements Serializable {


    public static String generateAccessToken(String username) {
        return  Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.getBytes(StandardCharsets.UTF_8))
                .compact();

    }
}
