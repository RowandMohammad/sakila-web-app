package com.sakila.sakilawebapp.service.implementation;

import com.sakila.sakilawebapp.dto.ActorDTO;
import com.sakila.sakilawebapp.dto.FilmDTO;
import com.sakila.sakilawebapp.entity.Category;
import com.sakila.sakilawebapp.entity.Film;
import com.sakila.sakilawebapp.entity.Language;
import com.sakila.sakilawebapp.exception.ResourceNotFoundException;
import com.sakila.sakilawebapp.repository.CategoryRepository;
import com.sakila.sakilawebapp.repository.FilmCategoryRepository;
import com.sakila.sakilawebapp.repository.FilmRepository;
import com.sakila.sakilawebapp.repository.LanguageRepository;
import com.sakila.sakilawebapp.service.FilmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private LanguageRepository languageRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FilmCategoryRepository filmCategoryRepository;

    @Override
    public List<FilmDTO> getFilmsByCategory(Byte categoryId) {
        return filmCategoryRepository.findAllByCategoryId(categoryId)
                .stream()
                .map(filmCategory -> modelMapper.map(filmCategory.getFilm(), FilmDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    @Override
    public List<FilmDTO> getAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(film -> modelMapper.map(film, FilmDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FilmDTO getFilmById(Short id) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Film not found"));
        return modelMapper.map(film, FilmDTO.class);
    }

    @Override
    public FilmDTO createFilm(FilmDTO filmDTO) {
        Film film = modelMapper.map(filmDTO, Film.class);

        if (filmDTO.getLanguageId() != null) {
            Language language = languageRepository.findById(filmDTO.getLanguageId())
                    .orElseThrow(() -> new ResourceNotFoundException("Language not found"));
            film.setLanguage(language);
        }

        Film savedFilm = filmRepository.save(film);
        return modelMapper.map(savedFilm, FilmDTO.class);
    }

    @Override
    public FilmDTO updateFilm(Short id, FilmDTO filmDTO) {
        Film film = filmRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Film not found"));
        if (filmDTO.getLanguageId() != null) {
            Language language = languageRepository.findById(filmDTO.getLanguageId())
                    .orElseThrow(() -> new ResourceNotFoundException("Language not found"));
            film.setLanguage(language);
        }
        modelMapper.map(filmDTO, film);
        Film savedFilm = filmRepository.save(film);
        return modelMapper.map(savedFilm, FilmDTO.class);
    }

    @Override
    public void deleteFilm(Short id) {
        filmRepository.deleteRelatedRentals(id);
        filmRepository.deleteRelatedInventory(id);
        filmRepository.deleteRelatedFilmCategory(id);
        filmRepository.deleteRelatedFilmActors(id);
        filmRepository.deleteById(id);
    }

    @Override
    public List<ActorDTO> getActorsByFilmId(Short filmId) {
        Film film = filmRepository.findById(filmId).orElseThrow(() -> new ResourceNotFoundException("Film not found"));
        return film.getFilmActors()
                .stream()
                .map(filmActor -> modelMapper.map(filmActor.getActor(), ActorDTO.class))
                .collect(Collectors.toList());
    }






}
