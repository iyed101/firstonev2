package com.animeproj.firstone.repo;

import java.util.List;

// Importez java.util.List

import org.springframework.data.jpa.repository.JpaRepository;

import com.animeproj.firstone.models.Anime;

public interface AnimeRepo extends JpaRepository<Anime, Integer> {
    List<Anime> findByType(String type);
}
