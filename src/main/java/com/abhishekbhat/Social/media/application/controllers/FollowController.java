package com.abhishekbhat.Social.media.application.controllers;

import com.abhishekbhat.Social.media.application.dtos.FollowDto;
import com.abhishekbhat.Social.media.application.models.Follow;
import com.abhishekbhat.Social.media.application.services.FollowService;
import com.abhishekbhat.Social.media.application.utils.api.ApiResponse;
import com.abhishekbhat.Social.media.application.utils.api.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/follow")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService){
        this.followService = followService;
    }

    // follow User
    @PostMapping("")
    ResponseEntity<ApiResponse<Follow>> followUser(@RequestBody @Valid FollowDto body, Authentication authentication) {
        Follow follow = followService.followUser(body.getFollowee(), UUID.fromString(authentication.getName()));

        return ResponseEntity.ok(
                ResponseUtil.success("User followed successfully", follow, null)
        );
    }

    @DeleteMapping("/")
    ResponseEntity<ApiResponse<String>> unfollowUser(@RequestParam UUID followeeId,Authentication authentication) {
        followService.unfollowUser(followeeId, UUID.fromString(authentication.getName()));
        return ResponseEntity.ok(
                ResponseUtil.success("Unfollowed successfully", "", null)
        );
    }
}
