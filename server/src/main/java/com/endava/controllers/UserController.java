package com.endava.controllers;

import com.endava.models.UserDto;
import com.endava.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/api/users")
@Tag(name = "1. User", description = "User API")
@CrossOrigin(origins = "http://localhost:3000")
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
            summary = "Confirm your email",
            description = "Confirms your email")
    @RequestMapping(
            value = "/confirmation",
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON_VALUE})
    public String confirmUserAccount(@RequestParam("token") String token, Model model)  {
        return userService.confirmUserAccount(token, model);
    }

    @Operation(summary = "Verify token for user", description = "Verifies the token for the user")
    @RequestMapping(value = "/verify/{token}",  method = RequestMethod.GET,
            headers = "Accept=application/json")
    public ResponseEntity<?> verifyToken(@PathVariable String token) {
        return userService.verifyToken(token);
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
    public ResponseEntity<?> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(
            summary = "Find user by id",
            description = "Finds the only user with the given id, if exists")
    @RequestMapping(
            value = "/{userId}",
            method = RequestMethod.GET,
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUserByUserId(@PathVariable("userId") UUID userId) {
        return userService.getUserByUserId(userId);
    }


    @Operation(
            summary = "Update user",
            description = "Updates the user with the given id")
    @RequestMapping(
            value = "/{userId}",
            method = RequestMethod.PUT,
            consumes = {APPLICATION_JSON_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateUser(@PathVariable("userId") UUID userId, @RequestBody UserDto user) {
        return userService.updateUser(userId, user);

    }
}
