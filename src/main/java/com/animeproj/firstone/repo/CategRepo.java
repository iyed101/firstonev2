package com.animeproj.firstone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animeproj.firstone.models.Categorie;

public interface CategRepo extends JpaRepository<Categorie, Integer> {
    
}
