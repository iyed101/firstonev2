    package com.animeproj.firstone.controller;

    import org.springframework.web.bind.annotation.RestController;

import com.animeproj.firstone.models.Anime;
import com.animeproj.firstone.models.AnimeCat;
import com.animeproj.firstone.models.AnimeCatDTO;
import com.animeproj.firstone.models.Categorie;
    import com.animeproj.firstone.repo.AnimeCatRepo;
import com.animeproj.firstone.repo.AnimeRepo;
import com.animeproj.firstone.repo.CategRepo;

import java.util.ArrayList;
import java.util.List;
    import java.util.Optional;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.PutMapping;


    @RestController
    @RequestMapping(value = "/categ")
    public class CategController {
        @Autowired
        private CategRepo categRepo;
        @Autowired
        private AnimeCatRepo animeCatRepo;
        @Autowired
        private AnimeRepo animeRepo;

        @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
        @CrossOrigin
        public List<Categorie> getAllCategories() {
            return categRepo.findAll();
        }
        @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Categorie> getCategorieById(@PathVariable("id") int id) {
            Optional<Categorie> categorie =categRepo.findById(id);
            return categorie.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }
        @SuppressWarnings("null")
        @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @CrossOrigin
        public ResponseEntity<Categorie> createCategorie(@RequestBody Categorie categorie) {
            try{
                Categorie savedCategorie = categRepo.save(categorie);
                return new ResponseEntity<>(savedCategorie, HttpStatus.CREATED);
            }
            catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @PutMapping(value = "update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Categorie> updateCategorie(@PathVariable("id") int id, @RequestBody Categorie categorieDetails) {
            Optional<Categorie> categorieData = categRepo.findById(id);
            if (categorieData.isPresent()) {
                Categorie categorie = categorieData.get();
                categorie.setNom(categorieDetails.getNom());
                Categorie updatedCategorie = categRepo.save(categorie);
                return new ResponseEntity<>(updatedCategorie, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        @DeleteMapping(value = "delete/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<Categorie> deleteCategorie(@PathVariable("id") int id) {
            try{
                Categorie deletedCategorie = categRepo.findById(id).get();
                categRepo.deleteById(id);
                return new ResponseEntity<>(deletedCategorie, HttpStatus.OK);
            }
            catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        @SuppressWarnings("null")
        @PostMapping(value = "/addCategToAnime", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        @CrossOrigin
    public ResponseEntity<List<AnimeCat>> AddAnCat(@RequestBody AnimeCatDTO anCatDTO) {
        try {
            Optional<Anime> anime = animeRepo.findById(anCatDTO.getAnimeId());
            if (!anime.isPresent()) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            List<AnimeCat> savedAnCats = new ArrayList<>();
            for (int categorieId : anCatDTO.getCategorieIds()) {
                Optional<Categorie> categorie = categRepo.findById(categorieId);
                if (categorie.isPresent()) {
                    AnimeCat anCat = new AnimeCat();
                    anCat.setAnime(anime.get());
                    anCat.setCtegorie(categorie.get());
                    AnimeCat savedAnCat = animeCatRepo.save(anCat);
                    savedAnCats.add(savedAnCat);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>(savedAnCats, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }   
    @SuppressWarnings("null")
    @GetMapping(value = "/animeByCateg/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Anime>> getAnimesByCategorie(@PathVariable("id") int id) {
        try {
            Optional<Categorie> categorie = categRepo.findById(id);
            if (categorie.isPresent()) {
                List<AnimeCat> animeCats = animeCatRepo.findByCategorie(categorie.get());
                List<Anime> animes = new ArrayList<>();
                for (AnimeCat animeCat : animeCats) {
                    animes.add(animeCat.getAnime());
                }
                return new ResponseEntity<>(animes, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    }
