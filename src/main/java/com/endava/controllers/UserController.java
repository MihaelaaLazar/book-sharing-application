package com.endava.controllers;

import com.endava.models.UserDto;
import com.endava.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<?> createUserAccount(@RequestBody UserDto user) throws MessagingException {
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
    public UserDto login(@RequestBody UserDto user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON_VALUE})
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @RequestMapping(
            value="/{userId}",
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON_VALUE})
    public UserDto getUserByUserId(@PathVariable("userId") UUID userId){
        return userService.getUserByUserId(userId);
    }
}
