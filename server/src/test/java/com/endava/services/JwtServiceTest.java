package com.endava.services;

import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import util.IntegrationTest;
import util.TestConstants;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@IntegrationTest
class JwtServiceTest {

    @InjectMocks
    private JwtUtilService jwtUtilService;

    @Test
    void shouldExtractUsername(){
        String token = jwtUtilService.generateToken(TestConstants.USERNAME);

        assertThat(jwtUtilService.extractUsername(token)).isEqualTo(TestConstants.USERNAME);
    }

    @Test
    void shouldReturnTrueIfTheTokenIsNotExpired(){
        String token = jwtUtilService.generateToken(TestConstants.USERNAME);

        assertThat(!jwtUtilService.isTokenExpired(token)).isTrue();
    }

    @Test
    void shouldThrownErrorIfTokenIsExpired(){
        assertThatThrownBy(() -> jwtUtilService.isTokenExpired(TestConstants.EXPIRED_TOKEN))
                .isInstanceOf(ExpiredJwtException.class)
                .hasMessageStartingWith("JWT expired");
    }
}
