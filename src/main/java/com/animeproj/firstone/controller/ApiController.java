package com.animeproj.firstone.controller;

import org.springframework.web.bind.annotation.RestController;
import com.animeproj.firstone.repo.AnimeRepo;
import com.animeproj.firstone.models.Anime;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(value = "/")
public class ApiController {
    @Autowired
    private AnimeRepo animeRepo;
    
    @GetMapping(value = "/wlc")
    public String getPage(){
        return "welcome";
    }

    @GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public List<Anime> getAnime(){
        List<Anime> animes= animeRepo.findAll();        
        return animes;
    }

    @SuppressWarnings("null")
    @PostMapping(value = "/anime", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Anime> postAnime(@RequestBody Anime anime) {
        try {
            Anime savedAnime = animeRepo.save(anime);
            return new ResponseEntity<>(savedAnime, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/anime/{id}")
    public ResponseEntity<Anime> deleteAnime(@PathVariable("id") int id) {
        try {
            Anime animedelet=animeRepo.findById(id).get();
            animeRepo.deleteById(id);
            return new ResponseEntity<>(animedelet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/anime/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Anime> updateAnime(@PathVariable("id") Integer id, @RequestBody Anime animeDetails) {
        Optional<Anime> animeData = animeRepo.findById(id);

        if (animeData.isPresent()) {
            Anime anime = animeData.get();
            anime.setNom(animeDetails.getNom());
            anime.setStudio(animeDetails.getStudio());
            anime.setNombreEpisode(animeDetails.getNombreEpisode());
            anime.setType(animeDetails.getType());
            anime.setDateDebut(animeDetails.getDateDebut());
            anime.setDateFin(animeDetails.getDateFin());
            anime.setTrancheAge(animeDetails.getTrancheAge());
            anime.setSeison(animeDetails.getSeison());
            anime.setPhoto(animeDetails.getPhoto());
            Anime updatedAnime = animeRepo.save(anime);
            return new ResponseEntity<>(updatedAnime, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @SuppressWarnings("null")
    @GetMapping(value = "/anime/type/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public ResponseEntity<List<Anime>> getAnimeByType(@PathVariable String type) {
    try {
        List<Anime> animeByType = animeRepo.findByType(type);
        if (animeByType.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(animeByType, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
@SuppressWarnings("null")
@GetMapping(value = "/anime/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public ResponseEntity<Anime> getAnimeById(@PathVariable int id) {
    try {
        Optional<Anime> animeData = animeRepo.findById(id);
        if (animeData.isPresent()) {
            return new ResponseEntity<>(animeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    } catch (Exception e) {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
}
