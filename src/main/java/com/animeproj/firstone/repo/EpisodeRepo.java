package com.animeproj.firstone.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animeproj.firstone.models.Episode;

public interface EpisodeRepo extends JpaRepository<Episode, Integer> {
    List<Episode> findByAnimeId(int animeId);
}
