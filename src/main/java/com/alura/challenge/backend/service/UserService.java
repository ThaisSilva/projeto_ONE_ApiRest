package com.alura.challenge.backend.service;

import com.alura.challenge.backend.dto.UserDTO;
import com.alura.challenge.backend.entities.User;
import com.alura.challenge.backend.repository.UserRepository;
import com.alura.challenge.backend.security.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public void createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));

        userRepository.save(user);
    }
}
