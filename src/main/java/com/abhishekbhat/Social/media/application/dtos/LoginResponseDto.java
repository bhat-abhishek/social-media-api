package com.abhishekbhat.Social.media.application.dtos;

import com.abhishekbhat.Social.media.application.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDto {
    private User user;
    private String token;
}

