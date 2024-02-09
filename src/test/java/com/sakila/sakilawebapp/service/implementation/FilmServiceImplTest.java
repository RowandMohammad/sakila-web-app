package com.sakila.sakilawebapp.service.implementation;

import com.sakila.sakilawebapp.dto.FilmDTO;
import com.sakila.sakilawebapp.entity.Film;
import com.sakila.sakilawebapp.entity.Language;
import com.sakila.sakilawebapp.exception.ResourceNotFoundException;
import com.sakila.sakilawebapp.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FilmServiceImplTest {

    @InjectMocks
    private FilmServiceImpl filmService;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private LanguageRepository languageRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private FilmCategoryRepository filmCategoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFilms() {
        Film film = new Film();
        when(filmRepository.findAll()).thenReturn(Collections.singletonList(film));
        when(modelMapper.map(film, FilmDTO.class)).thenReturn(new FilmDTO());

        assertNotNull(filmService.getAllFilms());
        verify(filmRepository).findAll();
    }

    @Test
    void testGetFilmById() {
        Film film = new Film();
        when(filmRepository.findById((short) 1)).thenReturn(Optional.of(film));
        when(modelMapper.map(film, FilmDTO.class)).thenReturn(new FilmDTO());

        assertNotNull(filmService.getFilmById((short) 1));
        verify(filmRepository).findById((short) 1);
    }

    @Test
    void testGetFilmByIdNotFound() {
        when(filmRepository.findById((short) 1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> filmService.getFilmById((short) 1));
    }

    @Test
    void testCreateFilm() {
        FilmDTO dto = new FilmDTO();
        dto.setLanguageId((byte) 1);
        Film film = new Film();
        Language language = new Language();
        when(modelMapper.map(dto, Film.class)).thenReturn(film);
        when(languageRepository.findById((byte) 1)).thenReturn(Optional.of(language));
        when(filmRepository.save(film)).thenReturn(film);
        when(modelMapper.map(film, FilmDTO.class)).thenReturn(dto);

        assertNotNull(filmService.createFilm(dto));
        verify(languageRepository).findById((byte) 1);
        verify(filmRepository).save(film);
    }

    @Test
    void testUpdateFilm() {
        FilmDTO dto = new FilmDTO();
        dto.setLanguageId((byte) 1);
        Film film = new Film();
        Language language = new Language();
        when(filmRepository.findById((short) 1)).thenReturn(Optional.of(film));
        when(languageRepository.findById((byte) 1)).thenReturn(Optional.of(language));
        when(filmRepository.save(film)).thenReturn(film);
        when(modelMapper.map(film, FilmDTO.class)).thenReturn(dto);

        assertNotNull(filmService.updateFilm((short) 1, dto));
        verify(filmRepository).findById((short) 1);
    }

    @Test
    void testDeleteFilm() {
        Short filmId = 1;
        doNothing().when(filmRepository).deleteRelatedRentals(filmId);
        doNothing().when(filmRepository).deleteRelatedInventory(filmId);
        doNothing().when(filmRepository).deleteRelatedFilmCategory(filmId);
        doNothing().when(filmRepository).deleteRelatedFilmActors(filmId);
        doNothing().when(filmRepository).deleteById(filmId);

        filmService.deleteFilm(filmId);

        verify(filmRepository).deleteRelatedRentals(filmId);
        verify(filmRepository).deleteRelatedInventory(filmId);
        verify(filmRepository).deleteRelatedFilmCategory(filmId);
        verify(filmRepository).deleteRelatedFilmActors(filmId);
        verify(filmRepository).deleteById(filmId);
    }

    @Test
    void testGetActorsByFilmId() {
        Short filmId = 1;
        Film film = new Film();
        film.setFilmActors(Collections.emptyList()); // No actors for simplicity.
        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));

        assertNotNull(filmService.getActorsByFilmId(filmId));
        verify(filmRepository).findById(filmId);
    }

    @Test
    void testGetActorsByFilmIdNotFound() {
        Short filmId = 1;
        when(filmRepository.findById(filmId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> filmService.getActorsByFilmId(filmId));
    }

    @Test
    void testSearchFilmsByTitle() {
        Film film = new Film();
        when(filmRepository.findByTitleContainingIgnoreCase("title")).thenReturn(Collections.singletonList(film));
        when(modelMapper.map(film, FilmDTO.class)).thenReturn(new FilmDTO());

        assertNotNull(filmService.searchFilmsByTitle("title"));
        verify(filmRepository).findByTitleContainingIgnoreCase("title");
    }
}
