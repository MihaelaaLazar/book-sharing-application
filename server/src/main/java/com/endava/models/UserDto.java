package com.endava.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class UserDto  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @Schema(hidden = true)
    private UUID userId;

    @Column(name = "first_name")
    @Schema(description = "First Name" ,required = true, example = "John")
    private String firstName;

    @Column(name = "last_name")
    @Schema(description = "Last Name" ,required = true, example = "Doe")
    private String lastName;

    @Column(name = "username")
    @Schema(description = "Username" ,required = true, example = "johndoe")
    private String username;

    @Column(name = "email")
    @Schema(description = "Email" ,required = true, example = "johndoe@gmail.com")
    private String email;

    @Column(name = "password")
    @Schema(description = "Password" ,required = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Schema(hidden = true)
    private boolean verified;

    @Schema(hidden = true)
    private String token;

    public UserDto(UUID userId) {
        this.userId = userId;
    }
}
