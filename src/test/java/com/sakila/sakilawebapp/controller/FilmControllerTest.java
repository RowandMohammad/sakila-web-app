package com.sakila.sakilawebapp.controller;

import com.sakila.sakilawebapp.dto.ActorDTO;
import com.sakila.sakilawebapp.dto.FilmDTO;
import com.sakila.sakilawebapp.entity.Category;
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
class FilmControllerTest {

    @InjectMocks
    private FilmController filmController;

    @Mock
    private FilmService filmService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllFilms() {
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
    void testGetFilmById() {
        FilmDTO film = new FilmDTO();
        film.setTitle("Film1");

        when(filmService.getFilmById((short) 1)).thenReturn(film);

        ResponseEntity<FilmDTO> response = filmController.getFilmById((short) 1);
        FilmDTO returnedFilm = response.getBody();

        assertEquals("Film1", returnedFilm.getTitle());
    }

    @Test
    void testCreateFilm() {
        FilmDTO film = new FilmDTO();
        film.setTitle("Film1");

        when(filmService.createFilm(film)).thenReturn(film);

        ResponseEntity<FilmDTO> response = filmController.createFilm(film);
        FilmDTO returnedFilm = response.getBody();

        assertEquals("Film1", returnedFilm.getTitle());
    }

    @Test
    void testUpdateFilm() {
        FilmDTO film = new FilmDTO();
        film.setTitle("UpdatedFilm");

        when(filmService.updateFilm((short) 1, film)).thenReturn(film);

        ResponseEntity<FilmDTO> response = filmController.updateFilm((short) 1, film);
        FilmDTO returnedFilm = response.getBody();

        assertEquals("UpdatedFilm", returnedFilm.getTitle());
    }

    @Test
    void testDeleteFilm() {
        filmController.deleteFilm((short) 1);
        verify(filmService, times(1)).deleteFilm((short) 1);
    }

    @Test
    void testGetAllCategories() {
        Category category1 = new Category();
        category1.setName("Category1");
        Category category2 = new Category();
        category2.setName("Category2");

        when(filmService.getAllCategories()).thenReturn(Arrays.asList(category1, category2));

        ResponseEntity<List<Category>> response = filmController.getAllCategories();
        List<Category> categories = response.getBody();

        assertEquals(2, categories.size());
        assertEquals("Category1", categories.get(0).getName());
        assertEquals("Category2", categories.get(1).getName());
    }

    @Test
    void testGetFilmsByCategory() {
        FilmDTO film1 = new FilmDTO();
        film1.setTitle("Film1");
        FilmDTO film2 = new FilmDTO();
        film2.setTitle("Film2");

        when(filmService.getFilmsByCategory((byte) 1)).thenReturn(Arrays.asList(film1, film2));

        ResponseEntity<List<FilmDTO>> response = filmController.getFilmsByCategory((byte) 1);
        List<FilmDTO> films = response.getBody();

        assertEquals(2, films.size());
        assertEquals("Film1", films.get(0).getTitle());
        assertEquals("Film2", films.get(1).getTitle());
    }

    @Test
    void testGetActorsByFilmId() {
        ActorDTO actor1 = new ActorDTO();
        actor1.setFirstName("Actor1");
        ActorDTO actor2 = new ActorDTO();
        actor2.setFirstName("Actor2");

        when(filmService.getActorsByFilmId((short) 1)).thenReturn(Arrays.asList(actor1, actor2));

        ResponseEntity<List<ActorDTO>> response = filmController.getActorsByFilmId((short) 1);
        List<ActorDTO> actors = response.getBody();

        assertEquals(2, actors.size());
        assertEquals("Actor1", actors.get(0).getFirstName());
        assertEquals("Actor2", actors.get(1).getFirstName());
    }

    @Test
    void testSearchFilms() {
        FilmDTO film1 = new FilmDTO();
        film1.setTitle("Film1");
        FilmDTO film2 = new FilmDTO();
        film2.setTitle("Film2");

        when(filmService.searchFilmsByTitle("Film")).thenReturn(Arrays.asList(film1, film2));

        ResponseEntity<List<FilmDTO>> response = filmController.searchFilms("Film");
        List<FilmDTO> films = response.getBody();

        assertEquals(2, films.size());
        assertEquals("Film1", films.get(0).getTitle());
        assertEquals("Film2", films.get(1).getTitle());
    }







}
