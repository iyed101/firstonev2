package com.animeproj.firstone.controller;

import com.animeproj.firstone.models.ListeAnUs;
import com.animeproj.firstone.models.Anime;
import com.animeproj.firstone.models.User;
import com.animeproj.firstone.repo.ListeAnUsRepo;
import com.animeproj.firstone.repo.AnimeRepo;
import com.animeproj.firstone.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/liste")
public class ListeController {

    @Autowired
    private ListeAnUsRepo listeAnUsRepo;

    @Autowired
    private AnimeRepo animeRepo;

    @Autowired
    private UserRepo userRepo;

    // Créer une nouvelle association
    @SuppressWarnings("null")
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListeAnUs> createListeAnUs(@RequestBody ListeAnUs listeAnUs) {
        try {
            Optional<Anime> anime = animeRepo.findById(listeAnUs.getAnime().getId());
            Optional<User> user = userRepo.findById(listeAnUs.getUser().getId());

            if (anime.isPresent() && user.isPresent()) {
                listeAnUs.setAnime(anime.get());
                listeAnUs.setUser(user.get());
                ListeAnUs savedListeAnUs = listeAnUsRepo.save(listeAnUs);
                return new ResponseEntity<>(savedListeAnUs, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Obtenir toutes les associations
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ListeAnUs> getAllListeAnUs() {
        return listeAnUsRepo.findAll();
    }

    // Obtenir une association par ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListeAnUs> getListeAnUsById(@PathVariable("id") int id) {
        Optional<ListeAnUs> listeAnUs = listeAnUsRepo.findById(id);
        return listeAnUs.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mettre à jour une association existante
    @SuppressWarnings("null")
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListeAnUs> updateListeAnUs(@PathVariable("id") int id, @RequestBody ListeAnUs listeAnUsDetails) {
        Optional<ListeAnUs> listeAnUsData = listeAnUsRepo.findById(id);
        if (listeAnUsData.isPresent()) {
            ListeAnUs listeAnUs = listeAnUsData.get();
            listeAnUs.setEtat(listeAnUsDetails.getEtat());
            Optional<Anime> anime = animeRepo.findById(listeAnUsDetails.getAnime().getId());
            Optional<User> user = userRepo.findById(listeAnUsDetails.getUser().getId());

            if (anime.isPresent() && user.isPresent()) {
                listeAnUs.setAnime(anime.get());
                listeAnUs.setUser(user.get());
                ListeAnUs updatedListeAnUs = listeAnUsRepo.save(listeAnUs);
                return new ResponseEntity<>(updatedListeAnUs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer une association par ID
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteListeAnUs(@PathVariable("id") int id) {
        try {
            listeAnUsRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
