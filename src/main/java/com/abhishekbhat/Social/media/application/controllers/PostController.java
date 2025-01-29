package com.abhishekbhat.Social.media.application.controllers;


import com.abhishekbhat.Social.media.application.dtos.PostDto;
import com.abhishekbhat.Social.media.application.models.Post;
import com.abhishekbhat.Social.media.application.services.PostService;
import com.abhishekbhat.Social.media.application.utils.api.ApiResponse;
import com.abhishekbhat.Social.media.application.utils.api.ResponseUtil;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse<Post>> createAPost(@RequestBody @Valid PostDto body, Authentication authentication){
        System.out.println("Authentication" + authentication);
        Post post = postService.createAPost(body, authentication.getName());

        return ResponseEntity.ok(
                ResponseUtil.success(
                        "Post created successfully",post, null
                )
        );
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<Post>>> getFeedForUser(Pageable pageable,Authentication authentication) {
        Page<Post> posts = postService.getFeedForUser(UUID.fromString(authentication.getName()), pageable);

        return ResponseEntity.ok(
                ResponseUtil.success("Feed fetched successfully", posts.stream().toList(), posts.getPageable())
        );
    }
}
