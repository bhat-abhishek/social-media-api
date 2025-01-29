package com.abhishekbhat.Social.media.application.repositories;

import com.abhishekbhat.Social.media.application.models.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface FollowRepository extends JpaRepository<Follow, UUID> {
    @Query("SELECT f FROM Follow f WHERE f.follower.id = :followerId AND f.followee.id = :followeeId")
    Optional<Follow> findByFollowerIdAndFolloweeId(@Param("followerId") UUID followerId, @Param("followeeId") UUID followeeId);
}
