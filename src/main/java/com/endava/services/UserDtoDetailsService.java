package com.endava.services;

import com.endava.models.UserDto;
import com.endava.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;

@Service
public class UserDtoDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try{
            UserDto userDto = userRepo.findByUsername(username);
            return new User(userDto.getUsername(), userDto.getPassword(), new ArrayList<>());
        }
        catch (Exception e){
            throw new NotFoundException("User not found");
        }
    }
}

