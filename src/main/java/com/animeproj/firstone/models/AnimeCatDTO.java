package com.animeproj.firstone.models;

import java.util.List;

public class AnimeCatDTO {
    private int animeId;
    private List<Integer> categorieIds;

    // Getters et Setters
    public int getAnimeId() {
        return animeId;
    }

    public void setAnimeId(int animeId) {
        this.animeId = animeId;
    }

    public List<Integer> getCategorieIds() {
        return categorieIds;
    }

    public void setCategorieIds(List<Integer> categorieIds) {
        this.categorieIds = categorieIds;
    }
}
