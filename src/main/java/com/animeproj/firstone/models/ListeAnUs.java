package com.animeproj.firstone.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class ListeAnUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "etat")
    private String etat;
    @ManyToOne
    @JoinColumn(name = "anime_id",referencedColumnName = "id")
    private Anime anime;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    public ListeAnUs() {
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getEtat() {
        return etat;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    
}
