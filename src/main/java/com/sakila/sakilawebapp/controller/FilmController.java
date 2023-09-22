package com.sakila.sakilawebapp.controller;

import com.sakila.sakilawebapp.dto.ActorDTO;
import com.sakila.sakilawebapp.dto.FilmDTO;
import com.sakila.sakilawebapp.entity.Category;
import com.sakila.sakilawebapp.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAllFilms() {
        List<FilmDTO> films = filmService.getAllFilms();
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDTO> getFilmById(@PathVariable Short id) {
        FilmDTO film = filmService.getFilmById(id);
        return new ResponseEntity<>(film, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FilmDTO> createFilm(@RequestBody FilmDTO filmDTO) {
        FilmDTO createdFilm = filmService.createFilm(filmDTO);
        return new ResponseEntity<>(createdFilm, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> updateFilm(@PathVariable Short id, @RequestBody FilmDTO filmDTO) {
        FilmDTO updatedFilm = filmService.updateFilm(id, filmDTO);
        return new ResponseEntity<>(updatedFilm, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Short id) {
        filmService.deleteFilm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = filmService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<FilmDTO>> getFilmsByCategory(@PathVariable Byte categoryId) {
        List<FilmDTO> films = filmService.getFilmsByCategory(categoryId);
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/{id}/actors")
    public ResponseEntity<List<ActorDTO>> getActorsByFilmId(@PathVariable Short id) {
        List<ActorDTO> actors = filmService.getActorsByFilmId(id);
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }

}
