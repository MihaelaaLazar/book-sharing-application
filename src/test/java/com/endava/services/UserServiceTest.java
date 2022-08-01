package com.endava.services;


import com.endava.config.PasswordConfig;
import com.endava.models.UserDto;
import com.endava.repositories.UserRepo;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import util.CreateCustomUser;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private final static UUID USER_ID = UUID.randomUUID();

    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordConfig passwordConfig;

    @InjectMocks
    private UserService userService;


    @Test
    @Ignore("Not implemented yet - need to mock the email service and jwt service")
    void createUserSuccessfully() {
        final var userId = UUID.randomUUID();
        final var email = "example@gmail.com";
        final var username = "username";
        final var password = "password";
        final var firstName = "firstName";
        final var lastName = "lastName";
        final var token = "token";
        final var user = new UserDto();

        user.setUserId(userId);
        user.setEmail(email);
        user.setToken(token);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(passwordConfig.encoder().encode(password));

        when(userRepo.save(any(UserDto.class))).thenReturn(user);


        final var actualUser = userService.createUserAccount(user);
        final var userVerified = Mockito.inOrder(userRepo, userService);
        userVerified.verify(userRepo).save(user);
        userVerified.verify(userService).createUserAccount(user);
        assertThat(actualUser).isSameAs(user);

    }

    @Test
    public void shouldFindTheUser() {
        final var user = CreateCustomUser.johnDoe();
        user.setUserId(USER_ID);

        when(userRepo.findByUserId(USER_ID)).thenReturn(user);

        final var actualResponse = userService.getUserByUserId(USER_ID);
        assertThat(actualResponse).isSameAs(user);
    }

    @Test
    public void shouldNotFindTheUser() {
        ResponseEntity<?> responseEntity = ResponseEntity
                .status(400)
                .body("User not found");

        when(userRepo.findByUserId(USER_ID)).thenReturn(null);
        assertThat(userService.getUserByUserId(USER_ID)).isEqualTo(responseEntity);

    }


    @Test
    public void shouldGetAllUsers() {
        when(userRepo.findAll()).thenReturn(List.of(new UserDto()));

        List<UserDto> users = userService.getAllUsers();

        assertThat(users).isNotNull();

    }

}
