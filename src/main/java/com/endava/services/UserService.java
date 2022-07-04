package com.endava.services;

import com.endava.config.PasswordConfig;
import com.endava.models.UserDto;
import com.endava.repositories.UserRepo;
import com.endava.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private EmailService emailService;

    public UserDto createUserAccount(UserDto user) throws MessagingException {
        user.setPassword(passwordConfig.encoder().encode(user.getPassword()));
        user.setVerified(false);
        String token = JwtTokenUtil.generateAccessToken(user);
        user.setToken(token);
        String url = "http://localhost:8080/api/users/confirmation?token=" + user.getToken();
        emailService.sendConfirmationEmail("Please confirm your account by clicking on: " + url, user.getEmail(), "Confirm your account");
        return userRepo.save(user);
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

    public String login(UserDto user) {
       userRepo.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (passwordConfig.encoder().matches(user.getPassword(), user.getPassword())) {
            if (user.isVerified()) {
                return user.getToken();
            } else {
                return "User account not verified";
            }
        } else {
            return "Invalid username or password";
        }
    }
        public List<UserDto> getAllUsers () {
            return userRepo.findAll();
        }

    }
