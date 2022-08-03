package com.endava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    public boolean matches(CharSequence rawPassword, String encodedPassword){
       return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

}
