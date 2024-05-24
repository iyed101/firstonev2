package com.animeproj.firstone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class AnimeCat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "anime_id",referencedColumnName = "id")
    private Anime anime;
    @ManyToOne
    @JoinColumn(name = "cat_id",referencedColumnName = "idCat")
    private Categorie categorie;
    public AnimeCat() {
        super();
    }
    public int getId() {
        return id;
    }
    public Anime getAnime() {
        return anime;
    }
    public void setAnime(Anime anime) {
        this.anime = anime;
    }
    public Categorie getCtegorie() {
        return categorie;
    }
    public void setCtegorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
