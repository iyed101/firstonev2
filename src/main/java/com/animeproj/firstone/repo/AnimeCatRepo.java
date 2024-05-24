package com.animeproj.firstone.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animeproj.firstone.models.AnimeCat;
import com.animeproj.firstone.models.Categorie;

public interface AnimeCatRepo extends JpaRepository<AnimeCat, Integer> {
    List<AnimeCat> findByCategorie(Categorie categorie);
}
