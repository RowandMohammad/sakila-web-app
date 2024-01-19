package com.sakila.sakilawebapp.service;

import com.sakila.sakilawebapp.dto.ActorDTO;
import com.sakila.sakilawebapp.dto.FilmDTO;
import com.sakila.sakilawebapp.entity.Category;

import java.util.List;

public interface FilmService {
    List<FilmDTO> getAllFilms();
    FilmDTO getFilmById(Short id);
    FilmDTO createFilm(FilmDTO filmDTO);
    FilmDTO updateFilm(Short id, FilmDTO filmDTO);
    void deleteFilm(Short id);
    List<Category> getAllCategories();
    List<FilmDTO> getFilmsByCategory(Byte categoryId);
    List<ActorDTO> getActorsByFilmId(Short filmId);
    List<FilmDTO> searchFilmsByTitle(String title);




}
