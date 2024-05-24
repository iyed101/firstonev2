package com.animeproj.firstone.controller;

import com.animeproj.firstone.models.Anime;
import com.animeproj.firstone.models.Episode;
import com.animeproj.firstone.repo.AnimeRepo;
import com.animeproj.firstone.repo.EpisodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/episode")
@CrossOrigin(origins = "http://localhost:3000")
public class EpisodeController {

    @Autowired
    private EpisodeRepo episodeRepo;
    @Autowired
    private AnimeRepo animeRepo;
    // Créer un nouvel épisode
    @SuppressWarnings("null")
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Episode> createEpisode(@RequestBody Episode episode) {
        try {
            // Vérifier que l'anime associé existe
            Optional<Anime> anime = animeRepo.findById(episode.getAnime().getId());
            if (anime.isPresent()) {
                episode.setAnime(anime.get());
                Episode savedEpisode = episodeRepo.save(episode);
                return new ResponseEntity<>(savedEpisode, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Obtenir tous les épisodes
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Episode> getAllEpisodes() {
        return episodeRepo.findAll();
    }

    // Obtenir un épisode par ID
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Episode> getEpisodeById(@PathVariable("id") int id) {
        Optional<Episode> episode = episodeRepo.findById(id);
        return episode.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Mettre à jour un épisode existant
    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Episode> updateEpisode(@PathVariable("id") int id, @RequestBody Episode episodeDetails) {
        Optional<Episode> episodeData = episodeRepo.findById(id);
        if (episodeData.isPresent()) {
            Episode episode = episodeData.get();
            episode.setAnime(episodeDetails.getAnime());
            episode.setNom(episodeDetails.getNom());
            episode.setNombre(episodeDetails.getNombre());
            episode.setDate(episodeDetails.getDate());
            episode.setPhoto(episodeDetails.getPhoto());
            episode.setContent(episodeDetails.getContent());
            Episode updatedEpisode = episodeRepo.save(episode);
            return new ResponseEntity<>(updatedEpisode, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Supprimer un épisode par ID
    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> deleteEpisode(@PathVariable("id") int id) {
        try {
            episodeRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @SuppressWarnings("null")
    @GetMapping(value = "/byAnime/{animeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Episode>> getEpisodesByAnimeId(@PathVariable("animeId") int animeId) {
        try {
            Optional<Anime> anime = animeRepo.findById(animeId);
            if (anime.isPresent()) {
                List<Episode> episodes = episodeRepo.findByAnimeId(animeId);
                return new ResponseEntity<>(episodes, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
