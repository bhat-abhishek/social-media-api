package com.abhishekbhat.Social.media.application.controllers;

import com.abhishekbhat.Social.media.application.dtos.RegisterDto;
import com.abhishekbhat.Social.media.application.models.User;
import com.abhishekbhat.Social.media.application.services.UserService;
import com.abhishekbhat.Social.media.application.utils.api.ApiResponse;
import com.abhishekbhat.Social.media.application.utils.api.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity<ApiResponse<User>> registerUser(@RequestBody @Valid RegisterDto body) {
        User user = userService.registerUser(body);
        return ResponseEntity.ok(
                ResponseUtil.success("User registered successfully", user, null)
        );
    }
}
