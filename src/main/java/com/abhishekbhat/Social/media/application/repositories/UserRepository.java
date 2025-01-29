package com.abhishekbhat.Social.media.application.repositories;

import com.abhishekbhat.Social.media.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
