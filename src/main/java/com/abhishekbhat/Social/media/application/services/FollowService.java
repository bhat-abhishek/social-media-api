package com.abhishekbhat.Social.media.application.services;

import com.abhishekbhat.Social.media.application.config.exceptions.ResourceNotFoundException;
import com.abhishekbhat.Social.media.application.models.Follow;
import com.abhishekbhat.Social.media.application.models.User;
import com.abhishekbhat.Social.media.application.repositories.FollowRepository;
import com.abhishekbhat.Social.media.application.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Optional;
import java.util.UUID;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    // follow user
    public Follow followUser(UUID followeeId,UUID followerId) {
        System.out.println("Follower Id" + followerId);
        System.out.println("Followee Id" + followeeId);

        Optional<User> follower = userRepository.findById(followerId);
        Optional<User> followee = userRepository.findById(followeeId);

        System.out.println("Follower" + follower.toString());
        System.out.println("Followee" + followee.toString());

        if(followee.isEmpty() || follower.isEmpty()) {
            throw new ResourceNotFoundException("Followee or Follower account not found");
        }

        Follow follow = Follow.builder()
                .follower(follower.get())
                .followee(followee.get())
                .build();
        return followRepository.save(follow);
    }

    public void unfollowUser(UUID followeeId, UUID followerId) {
        Optional<Follow> follow = followRepository.findByFollowerIdAndFolloweeId(followeeId, followerId);

        if(follow.isEmpty()) {
            throw  new ResourceNotFoundException("User is not following the Followee");
        }

        followRepository.deleteById(follow.get().getId());
        return;
    }
}
