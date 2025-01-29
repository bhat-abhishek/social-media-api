package com.abhishekbhat.Social.media.application.repositories;

import com.abhishekbhat.Social.media.application.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("""
        SELECT p FROM Post p
        LEFT JOIN Follow f ON p.user.id = f.followee.id AND f.follower.id = :userId
        ORDER BY CASE WHEN f.id IS NOT NULL THEN 1 ELSE 2 END,p.createdAt DESC
    """)
    Page<Post> findFeedForUser(@Param("userId") UUID userId, Pageable pageable);
}
