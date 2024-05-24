package com.animeproj.firstone.models;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String nom;
    @Column(name = "Studio")
    private String studio;
    @Column(name = "Episodes/Tom")
    private int nombreEpisode;
    @Column(name = "Type")
    private String type;
    @Column(name = "Date_debut")
    private Date dateDebut;
    @Column(name = "Date_fin")
    private Date dateFin;
    @Column(name = "photo")
    private String photo;
    @Column(name= "tranche_age")
    private String trancheAge;
    @Column(name= "seison")
    private String seison;
    public Anime() {
        super();
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getStudio() {
        return studio;
    }
    public void setStudio(String studio) {
        this.studio = studio;
    }
    public int getNombreEpisode() {
        return nombreEpisode;
    }
    public void setNombreEpisode(int nombreEpisode) {
        this.nombreEpisode = nombreEpisode;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public String getTrancheAge() {
        return trancheAge;
    }
    public void setTrancheAge(String trancheAge) {
        this.trancheAge = trancheAge;
    }
    public String getSeison() {
        return seison;
    }
    public void setSeison(String seison) {
        this.seison = seison;
    }
}
