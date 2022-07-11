package com.endava.controllers;

import com.endava.models.UserDto;
import com.endava.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/users")
@Tag(name = "1. User", description = "User API")
public class UserController {

    @Autowired
    private UserService userService;


    @Operation(
            summary = "Create user",
            description = "Adds a new user")
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            consumes = {APPLICATION_JSON_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createUserAccount(@RequestBody UserDto user) {
        return userService.createUserAccount(user);
    }

    @Operation(
            summary="Confirm your email",
            description = "Confirms your email")
    @RequestMapping(
            value = "/confirmation",
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON_VALUE})
    public String confirmUserAccount(@RequestParam("token") String token) {
        return userService.confirmUserAccount(token);
    }


    @Operation(
            summary = "Login",
            description = "Login")
    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            consumes = {APPLICATION_JSON_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> login(@RequestBody UserDto user) {
        return userService.login(user);
    }

    @Operation(
            summary = "Find all users",
            description = "Finds all users")
    @RequestMapping(
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON_VALUE})
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(
            summary = "Find user by id",
            description = "Finds the only user with the given id, if exists")
    @RequestMapping(
            value = "/{userId}",
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON_VALUE})
    public UserDto getUserByUserId(@PathVariable("userId") UUID userId) {
        return userService.getUserByUserId(userId);
    }
}
