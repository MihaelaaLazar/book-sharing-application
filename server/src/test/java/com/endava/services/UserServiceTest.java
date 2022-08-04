package com.endava.services;


import com.endava.config.PasswordConfig;
import com.endava.models.UserDto;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import util.IntegrationTest;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@IntegrationTest
class UserServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @SpyBean
    PasswordConfig passwordConfig;

    @Test
    @Ignore("This test is not working properly")
    void createUserSuccessfully(){
        final var request = new UserDto(
                UUID.fromString("92f85485-c781-46df-b49a-a0e963785017"),
                "John",
                "Doe",
                "johndoe",
                "johndoe1@gmail.com",
                "password",
                false,
                null);

        final var response = restTemplate.postForEntity("/api/users/register", request, UserDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(request);
    }



}
