package com.animeproj.firstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animeproj.firstone.models.ListeAnUs;

public interface ListeAnUsRepo extends JpaRepository<ListeAnUs, Integer> {
    
}
