package com.endava.services;


import com.endava.models.UserDto;
import com.endava.repositories.UserRepo;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final String EMAIL = "example@gmail.com";
    private static final String TOKEN = "token";
    private static final UUID USER_ID = UUID.randomUUID();
    private static final UserDto USER = new UserDto();

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldGetUserById() {
        // Arrange
        when(userRepo.findByUserId(USER_ID)).thenReturn(new UserDto());

        // Act
        UserDto user = userService.getUserByUserId(USER_ID);

        // Assert
        assertThat(user).isNotNull();
    }

    @Test
    public void shouldGetAllUsers() {
        when(userRepo.findAll()).thenReturn(List.of(new UserDto()));

        List<UserDto> users = userService.getAllUsers();

        assertThat(users).isNotNull();

    }

    @Test
    public void shouldReturnTheTokenWhenUserLogin() {
        when(userRepo.findByUsername(USER.getUsername())).thenReturn(USER);

        String token = String.valueOf(userService.login(USER));

        assertThat(token).isNotNull();
    }

    @Test
    public void shouldConfirmAccount() {
        when(userRepo.findByToken(TOKEN)).thenReturn(USER);

        String message = userService.confirmUserAccount(TOKEN);

        assertThat(message).isEqualTo("User account confirmed");
    }

    @Test
    public void shouldReturnAccountNotFoundIfTheTokenIsInvalid() {
        when(userRepo.findByToken(TOKEN)).thenReturn(null);

        String message = userService.confirmUserAccount(TOKEN);

        assertThat(message).isEqualTo("User account not found");
    }


}
