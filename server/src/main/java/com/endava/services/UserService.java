package com.endava.services;

import com.endava.config.PasswordConfig;
import com.endava.models.UserDto;
import com.endava.repositories.UserRepo;
import com.endava.validation.EmailValidation;
import io.jsonwebtoken.ExpiredJwtException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.mail.MessagingException;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDtoDetailsService userDtoDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    public ResponseEntity<?> createUserAccount(UserDto user) {
        try {
            user.setPassword(passwordConfig.encoder().encode(user.getPassword()));
            user.setVerified(false);
            if (userRepo.findByEmail(user.getEmail()).isPresent() && user.isVerified()) {
                return ResponseEntity.badRequest().body("Email already exists and your account is verified");
            } else {
                if (EmailValidation.isValidEmailAddress(user.getEmail())) {
                    String token = jwtUtilService.generateToken(user.getUsername());
                    user.setToken(token);
                    String url = "http://localhost:8080/api/users/confirmation?token=" + user.getToken();
                    Map<String, Object> template = new HashMap<>();
                    template.put("username", user.getUsername());
                    template.put("url", url);
                    emailService.sendEmailWithThymeleaf(user.getEmail(), "Confirm your account", template);
                    userRepo.save(user);
                    return ResponseEntity
                            .status(201)
                            .body("Your account has been created. Please check your email to confirm your account");
                }
            }
            return ResponseEntity
                    .status(400)
                    .body("Invalid email address");
        } catch (MessagingException e) {
            return ResponseEntity
                    .status(400)
                    .body("Email sending failed");
        }
    }

    public String confirmUserAccount(String token, Model model) {
        final String registerLink = "http://localhost:3000/register";
        final String loginLink = "http://localhost:3000/login";
        UserDto user = userRepo.findByToken(token);
        if (user != null && !user.isVerified()) {
            try {
                if (Boolean.FALSE.equals(jwtUtilService.isTokenExpired(token))) {
                    user.setVerified(true);
                    userRepo.save(user);
                    model.addAttribute("message", "Your account has been verified");
                    model.addAttribute("url", loginLink);
                    return "confirmation-template";
                } else {
                    userRepo.delete(user);
                    model.addAttribute("message", "Invalid token");
                    model.addAttribute("url", registerLink);
                    return "email-not-confirmed";
                }
            } catch (ExpiredJwtException e) {
                userRepo.delete(user);
                model.addAttribute("message", "Invalid token");
                model.addAttribute("url", registerLink);
                return "email-not-confirmed";

            }
        }
        model.addAttribute("message", "Your account has been verified");
        model.addAttribute("url", loginLink);
        return "confirmation-template";
    }


    public ResponseEntity<?> login(UserDto userDto) {
        try {
            UserDto _user = userRepo.findByUsername(userDto.getUsername());
            if (!_user.isVerified()) {
                return ResponseEntity
                        .status(404)
                        .body("Your account is not verified yet. Please check your email to confirm your account");
            }
            String encodedPassword = _user.getPassword();
            if (passwordConfig.matches(userDto.getPassword(), encodedPassword)) {
                try {
                    authenticationManager
                            .authenticate(new UsernamePasswordAuthenticationToken(
                                    userDto.getUsername(),
                                    userDto.getPassword())
                            );
                } catch (BadCredentialsException e) {
                    return ResponseEntity
                            .status(400)
                            .body("Invalid username or password");
                }
                final UserDetails userDetails = userDtoDetailsService.loadUserByUsername(userDto.getUsername());
                final String token = jwtUtilService.generateToken(userDetails.getUsername());
                _user.setToken(token);
                return ResponseEntity
                        .status(200)
                        .body(_user);
            } else {
                return ResponseEntity
                        .status(401)
                        .body("Unauthorized");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(409)
                    .body("Something went wrong");
        }
    }

    public ResponseEntity<?> updateUser(UUID userId, UserDto userDto) {
        UserDto existingUser = userRepo.findByUserId(userId);
        if (existingUser != null) {
            BeanUtils.copyProperties(userDto, existingUser, "userId", "email", "token", "password", "verified");
            userRepo.save(existingUser);
            return ResponseEntity
                    .status(200)
                    .body(existingUser);
        }
        return ResponseEntity.status(400).body("User not found");
    }

    public List<UserDto> getAllUsers() {
        return userRepo.findAll();
    }

    public ResponseEntity<?> getUserByUserId(UUID userId) {
        if (userRepo.findByUserId(userId) == null) {
            return ResponseEntity
                    .status(400)
                    .body("User not found");
        } else {
            UserDto user = userRepo.findByUserId(userId);
            return ResponseEntity.status(200).body(user);
        }
    }

    public ResponseEntity<?> verifyToken(String token) {
        JSONObject jsonObject = new JSONObject();
        String message = "message";
        try {
            if (Boolean.TRUE.equals(jwtUtilService.isTokenExpired(token))) {
                jsonObject.put(message, "Token expired");
                return ResponseEntity
                        .status(401)
                        .body(jsonObject.toString());
            } else {
                String username = jwtUtilService.extractUsername(token);
                UserDto user = userRepo.findByUsername(username);
                if (user != null) {
                    return ResponseEntity
                            .status(200)
                            .body(user);
                } else {
                    jsonObject.put(message, "User not found");
                    return ResponseEntity
                            .status(404)
                            .body(jsonObject.toString());
                }
            }
        } catch (ExpiredJwtException e) {
            final String expiredMsg = e.getMessage();
            jsonObject.put(message, expiredMsg);
            return ResponseEntity
                    .status(401)
                    .body(jsonObject.toString());
        }
    }

}
