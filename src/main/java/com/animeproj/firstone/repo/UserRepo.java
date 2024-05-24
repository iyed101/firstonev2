package com.animeproj.firstone.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animeproj.firstone.models.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
