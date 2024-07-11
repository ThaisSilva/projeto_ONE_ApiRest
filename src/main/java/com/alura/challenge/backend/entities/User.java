package com.alura.challenge.backend.entities;

import com.alura.challenge.backend.dto.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Data
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;

    @NotNull
    @Email
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;

    @NotNull
    @Size(min = 6)
    @Column(name = "password")
    private String password;


    @ManyToMany
    @JoinTable(
            name = "user_profile",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "profile_id")
    )
    private List<Profile> profiles;

    public void toEntity(UserDTO userDTO) {
        if (isNotBlank(userDTO.getName())) {
            this.name = userDTO.getName();
        }
        if (isNotBlank(userDTO.getPassword())) {
            this.name = userDTO.getPassword();
        }
        if (isNotBlank(userDTO.getEmail())) {
            this.name = userDTO.getEmail();
        }
    }

}
