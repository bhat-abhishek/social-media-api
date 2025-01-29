package com.abhishekbhat.Social.media.application.services;

import com.abhishekbhat.Social.media.application.config.exceptions.ResourceNotFoundException;
import com.abhishekbhat.Social.media.application.dtos.PostDto;
import com.abhishekbhat.Social.media.application.models.Post;
import com.abhishekbhat.Social.media.application.models.User;
import com.abhishekbhat.Social.media.application.repositories.PostRepository;
import com.abhishekbhat.Social.media.application.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public Post createAPost(PostDto content,String id) {
        // fetch the user
        User user = userRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new ResourceNotFoundException("User Does not exists")
        );

        Post post = Post.builder()
                .content(content.getContent())
                .user(user)
                .build();

        return postRepository.save(post);
    }

    public Page<Post> getFeedForUser(UUID userId, Pageable pageable) {
        return postRepository.findFeedForUser(userId,pageable);
    }

}
