package com.abhishekbhat.Social.media.application.services;

import com.abhishekbhat.Social.media.application.config.exceptions.ResourceNotFoundException;
import com.abhishekbhat.Social.media.application.config.exceptions.ValidationException;
import com.abhishekbhat.Social.media.application.dtos.RegisterDto;
import com.abhishekbhat.Social.media.application.models.User;
import com.abhishekbhat.Social.media.application.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService( UserRepository userRepository,PasswordEncoder passwordEncoder ) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public User registerUser(RegisterDto body) {

        // check already account exists
       Optional<User> userExists = userRepository.findByEmail(body.getEmail());

       if(userExists.isPresent()) {
        throw new ValidationException("Account already exists");
       }

        // hash the password
        User user = User.builder()
                .firstName(body.getFirstName())
                .lastName(body.getLastName())
                .email(body.getEmail())
                .password(hashPassword(body.getPassword()))
                .build();

        return userRepository.save(user);
    }

    private String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
