package com.abhishekbhat.Social.media.application.controllers;

import com.abhishekbhat.Social.media.application.dtos.LoginDto;
import com.abhishekbhat.Social.media.application.dtos.LoginResponseDto;
import com.abhishekbhat.Social.media.application.models.User;
import com.abhishekbhat.Social.media.application.services.AuthService;
import com.abhishekbhat.Social.media.application.utils.api.ApiResponse;
import com.abhishekbhat.Social.media.application.utils.api.ResponseUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@RequestBody @Valid LoginDto body, HttpServletResponse response) {
        LoginResponseDto loginResponseDto = authService.login(body);

        // set the cookie
        Cookie cookie = new Cookie("token", loginResponseDto.getToken());
        cookie.setPath("/");
        cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
        cookie.setHttpOnly(true);
        cookie.setSecure(false);

        response.addCookie(cookie);

        return ResponseEntity.ok(
                ResponseUtil.success("User logged in successfully", loginResponseDto.getUser(), null)
        );
    }





}
