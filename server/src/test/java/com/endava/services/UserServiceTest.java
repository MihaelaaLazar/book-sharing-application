//package com.endava.services;
//
//
//import com.endava.models.UserDto;
//import org.junit.jupiter.api.Test;
//
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.*;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.HttpStatus;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.UUID;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//
//@ActiveProfiles("test")
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
// class UserServiceTest {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    void createUserSuccessfully(){
//        final var request = new UserDto(
//                UUID.fromString("92f85485-c781-46df-b49a-a0e963785017"),
//                "John",
//                "Doe",
//                "johndoe",
//                "johndoe1@gmail.com",
//                "password",
//                false,
//                null);
//        final var response = restTemplate.postForEntity("/api/users", request, UserDto.class);
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isEqualTo(request);
//    }
//
//
//}
