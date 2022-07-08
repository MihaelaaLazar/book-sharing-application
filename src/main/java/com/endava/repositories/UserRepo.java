package com.endava.repositories;


import com.endava.models.UserDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends CrudRepository<UserDto, String> {
    Optional<UserDto> findByEmail(String email);

    UserDto findByUsername(String username);

    List<UserDto> findAll();

    UserDto findByUserId(UUID userId);

    @Query("SELECT u FROM UserDto u WHERE u.username = ?1 AND u.password = ?2")
    UserDto findByUsernameAndPassword(String username, String password);

    UserDto findByToken(String token);
}

