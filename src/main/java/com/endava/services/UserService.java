package com.endava.services;

import com.endava.config.PasswordConfig;
import com.endava.models.UserDto;
import com.endava.repositories.UserRepo;
import com.endava.validation.EmailValidation;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.UUID;

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
                    emailService.sendConfirmationEmail("Please confirm your account by clicking on: " + url, user.getEmail(), "Confirm your account");
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

    public String confirmUserAccount(String token) {
        UserDto user = userRepo.findByToken(token);
        if (user != null) {
            user.setVerified(true);
            userRepo.save(user);
            return "User account confirmed";
        } else {
            return "User account not found";
        }
    }

    public ResponseEntity<?> login(UserDto userDto) {
        try {
            UserDto _user = userRepo.findByUsername(userDto.getUsername());
            String encodedPassword = _user.getPassword();
            if (passwordConfig.matches(userDto.getPassword(), encodedPassword)) {
                try {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
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
                    .status(400)
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

    public UserDto getUserByUserId(UUID userId) {
        return userRepo.findByUserId(userId);
    }

    public ResponseEntity<?> verifyToken(String token) {
        try {
            if (jwtUtilService.isTokenExpired(token) == true) {
                return ResponseEntity
                        .status(401)
                        .body("Token expired");
            } else {
                String username = jwtUtilService.extractUsername(token);
                UserDto user = userRepo.findByUsername(username);
                if (user != null) {
                    return ResponseEntity
                            .status(200)
                            .body(user);
                } else {
                    return ResponseEntity
                            .status(404)
                            .body("User not found");
                }
            }
        } catch (ExpiredJwtException e) {
            final String expiredMsg = e.getMessage();
            return ResponseEntity
                    .status(401)
                    .body(expiredMsg);
        }
    }

}
