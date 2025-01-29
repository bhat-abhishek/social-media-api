package com.abhishekbhat.Social.media.application.services;

import com.abhishekbhat.Social.media.application.config.exceptions.UnauthorizedException;
import com.abhishekbhat.Social.media.application.dtos.LoginDto;
import com.abhishekbhat.Social.media.application.dtos.LoginResponseDto;
import com.abhishekbhat.Social.media.application.models.User;
import com.abhishekbhat.Social.media.application.repositories.UserRepository;
import com.abhishekbhat.Social.media.application.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDto login(LoginDto body){

        User user = userRepository.findByEmail(body.getEmail())
                .orElseThrow(
                        () ->  new UnauthorizedException("Invalid email or password")
                );

        if(!isPasswordCorrect(body.getPassword(), user.getPassword()) ) {
            throw new UnauthorizedException("Invalid email or password");
        }

        // generate the token
        String token = jwtUtil.generateToken(
                user.getEmail(),
                user.getId(),
                15
        );

        return new LoginResponseDto(user, token);
    }

    private boolean isPasswordCorrect(String password, String hashedPassword) {
        return passwordEncoder.matches(password,hashedPassword);
    }

}
