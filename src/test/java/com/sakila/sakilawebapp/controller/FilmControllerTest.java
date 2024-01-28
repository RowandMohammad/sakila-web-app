package com.sakila.sakilawebapp.controller;

import com.sakila.sakilawebapp.dto.FilmDTO;
import com.sakila.sakilawebapp.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmControllerTest {

    @InjectMocks
    private FilmController filmController;

    @Mock
    private FilmService filmService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllFilms() {
        FilmDTO film1 = new FilmDTO();
        film1.setTitle("Film1");
        FilmDTO film2 = new FilmDTO();
        film2.setTitle("Film2");

        when(filmService.getAllFilms()).thenReturn(Arrays.asList(film1, film2));

        ResponseEntity<List<FilmDTO>> response = filmController.getAllFilms();
        List<FilmDTO> films = response.getBody();

        assertEquals(2, films.size());
        assertEquals("Film1", films.get(0).getTitle());
        assertEquals("Film2", films.get(1).getTitle());
    }

    @Test
    public void testGetFilmById() {
        FilmDTO film = new FilmDTO();
        film.setTitle("Film1");

        when(filmService.getFilmById((short) 1)).thenReturn(film);

        ResponseEntity<FilmDTO> response = filmController.getFilmById((short) 1);
        FilmDTO returnedFilm = response.getBody();

        assertEquals("Film1", returnedFilm.getTitle());
    }

    @Test
    public void testCreateFilm() {
        FilmDTO film = new FilmDTO();
        film.setTitle("Film1");

        when(filmService.createFilm(film)).thenReturn(film);

        ResponseEntity<FilmDTO> response = filmController.createFilm(film);
        FilmDTO returnedFilm = response.getBody();

        assertEquals("Film1", returnedFilm.getTitle());
    }

    @Test
    public void testUpdateFilm() {
        FilmDTO film = new FilmDTO();
        film.setTitle("UpdatedFilm");

        when(filmService.updateFilm((short) 1, film)).thenReturn(film);

        ResponseEntity<FilmDTO> response = filmController.updateFilm((short) 1, film);
        FilmDTO returnedFilm = response.getBody();

        assertEquals("UpdatedFilm", returnedFilm.getTitle());
    }

    @Test
    public void testDeleteFilm() {
        filmController.deleteFilm((short) 1);
        verify(filmService, times(1)).deleteFilm((short) 1);
    }

}
