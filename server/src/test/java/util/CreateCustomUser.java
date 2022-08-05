package util;

import com.endava.models.UserDto;


import java.util.UUID;


public class CreateCustomUser {

    public static UserDto johnDoe() {
        UserDto user = new UserDto();
        user.setUserId(UUID.randomUUID());
        user.setEmail("johndoe1@gmail.com");
        user.setUsername("jonDoe");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setPassword("password");
        user.setVerified(false);
        user.setToken(null);
        return user;

    }

}
