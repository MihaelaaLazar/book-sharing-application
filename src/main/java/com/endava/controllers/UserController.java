package com.endava.controllers;

import com.endava.models.UserDto;
import com.endava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.util.List;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(
            value="/register",
            method = RequestMethod.POST,
            consumes = {APPLICATION_JSON_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public UserDto createUserAccount(@RequestBody UserDto user) throws MessagingException {
        return userService.createUserAccount(user);
    }

    @RequestMapping(
            value="/confirmation",
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON_VALUE})
    public String confirmUserAccount(@RequestParam("token") String token) {
        return userService.confirmUserAccount(token);
    }

    @RequestMapping(
            value="/login",
            method = RequestMethod.POST,
            consumes = {APPLICATION_JSON_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public String login(UserDto user) {
        return userService.login(user);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON_VALUE})
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

}
