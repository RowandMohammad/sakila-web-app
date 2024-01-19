package com.sakila.sakilawebapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakila.sakilawebapp.dto.ActorDTO;
import com.sakila.sakilawebapp.dto.FilmDTO;
import com.sakila.sakilawebapp.entity.Category;
import com.sakila.sakilawebapp.service.FilmService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private FilmService filmService;

    @InjectMocks
    private FilmController filmController;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(filmController).build();
    }

    @Test
    public void getAllFilms_ShouldReturnFilms() throws Exception {
        FilmDTO film1 = new FilmDTO();
        film1.setFilmId((short)1);
        film1.setTitle("Film A");
        film1.setDescription("Film A Description");

        when(filmService.getAllFilms()).thenReturn(Arrays.asList(film1));

        mockMvc.perform(get("/films"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].filmId").value(1))
                .andExpect(jsonPath("$[0].title").value("Film A"))
                .andExpect(jsonPath("$[0].description").value("Film A Description"));
    }

    @Test
    public void getFilmById_ShouldReturnFilm() throws Exception {
        FilmDTO film = new FilmDTO();
        film.setFilmId((short)2);
        film.setTitle("Film B");
        film.setDescription("Film B Description");

        when(filmService.getFilmById((short)2)).thenReturn(film);

        mockMvc.perform(get("/films/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.filmId").value(2))
                .andExpect(jsonPath("$.title").value("Film B"))
                .andExpect(jsonPath("$.description").value("Film B Description"));
    }



    @Test
    public void deleteFilm_ShouldReturnNoContent() throws Exception {
        Short filmId = 4;
        doNothing().when(filmService).deleteFilm(filmId);

        mockMvc.perform(delete("/films/" + filmId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getAllCategories_ShouldReturnCategories() throws Exception {
        Category category = new Category();
        category.setName("Drama");
        when(filmService.getAllCategories()).thenReturn(Collections.singletonList(category));

        mockMvc.perform(get("/films/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Drama"));
    }

    @Test
    public void getFilmsByCategory_ShouldReturnFilms() throws Exception {
        Byte categoryId = 5;
        FilmDTO filmInCategory = new FilmDTO();
        filmInCategory.setTitle("Drama Film");
        when(filmService.getFilmsByCategory(categoryId)).thenReturn(Collections.singletonList(filmInCategory));

        mockMvc.perform(get("/films/category/" + categoryId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Drama Film"));
    }

    @Test
    public void getActorsByFilmId_ShouldReturnActors() throws Exception {
        Short filmId = 6;
        ActorDTO actor = new ActorDTO();
        actor.setFirstName("John");
        actor.setLastName("Doe");
        when(filmService.getActorsByFilmId(filmId)).thenReturn(Collections.singletonList(actor));

        mockMvc.perform(get("/films/" + filmId + "/actors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"));
    }
}


