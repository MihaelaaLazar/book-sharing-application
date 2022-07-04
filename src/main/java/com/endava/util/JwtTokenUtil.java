package com.endava.util;

import com.endava.models.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.util.Date;
public class JwtTokenUtil implements Serializable {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // expires in 24 hours
    private static String SECRET_KEY = "secret";

    public static String generateAccessToken(UserDto user) {
        return Jwts.builder()
                .setSubject(String.format("%s,%s", user.getUserId(), user.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}
