package com.endava.services;

import com.endava.config.PasswordConfig;
import com.endava.models.UserDto;
import com.endava.repositories.UserRepo;
import com.endava.utils.JwtTokenUtil;
import com.endava.validation.EmailValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> createUserAccount(UserDto user) {
        try {
            user.setPassword(passwordConfig.encoder().encode(user.getPassword()));
            user.setVerified(false);
            if (userRepo.findByEmail(user.getEmail()).isPresent() && user.isVerified()) {
                return ResponseEntity.badRequest().body("Email already exists and your account is verified");
            } else {
                if (EmailValidation.isValidEmailAddress(user.getEmail())) {
                    String token = JwtTokenUtil.generateAccessToken(user.getUsername());
                    user.setToken(token);
                    String url = "http://localhost:8080/api/users/confirmation?token=" + user.getToken();
                    emailService.sendConfirmationEmail("Please confirm your account by clicking on: " + url, user.getEmail(), "Confirm your account");
                    userRepo.save(user);
                    return ResponseEntity.ok(user);
                }
            }
            return ResponseEntity.badRequest().body("Invalid email address");
        } catch (MessagingException e) {
            return ResponseEntity.badRequest().body("Email sending failed");
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

        UserDto _user = userRepo.findByUsername(userDto.getUsername());
        String encodedPassword = _user.getPassword();

        if (passwordConfig.matches(userDto.getPassword(), encodedPassword)) {
            return ResponseEntity
                    .status(200)
                    .body(Map.of("token", _user.getToken(), "verified", _user.isVerified()));
        } else {
            return ResponseEntity
                    .status(401)
                    .body("Unauthorized");
        }

    }
    public ResponseEntity<?> updateUser(UUID userId, UserDto userDto){
        UserDto existingUser = userRepo.findByUserId(userId);
        if(existingUser != null){
            BeanUtils.copyProperties(userDto, existingUser, "userId", "email", "token", "password", "verified");
            userRepo.save(existingUser);
            return ResponseEntity.ok(existingUser);
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    public List<UserDto> getAllUsers() {
        return userRepo.findAll();
    }

    public UserDto getUserByUserId(UUID userId) {
        return userRepo.findByUserId(userId);
    }

}
