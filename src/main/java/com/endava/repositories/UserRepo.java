package com.endava.repositories;


import com.endava.models.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends CrudRepository<UserDto, String> {
    Optional<UserDto> findByEmail(String email);
    List<UserDto> findAll();

    UserDto findByUserId(UUID userId);
    UserDto findByUsernameAndPassword(String username, String password);
    UserDto findByToken(String token);
}

