package com.abhishekbhat.Social.media.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class FollowDto {
    private UUID followee;
}
