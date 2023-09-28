package com.sakila.sakilawebapp.controller;

import com.sakila.sakilawebapp.dto.DashboardStats;
import com.sakila.sakilawebapp.dto.FilmDTO;
import com.sakila.sakilawebapp.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DashboardControllerTest {

    @InjectMocks
    private DashboardController dashboardController;

    @Mock
    private DashboardService dashboardService;

    @Test
    public void testGetDashboardStats() {
        DashboardStats stats = new DashboardStats();
        stats.setTotalActors(100);
        stats.setTotalFilms(200);
        stats.setTotalCategories(10);
        stats.setTotalLanguages(5);

        when(dashboardService.getDashboardStats()).thenReturn(stats);

        ResponseEntity<DashboardStats> response = dashboardController.getDashboardStats();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getTotalActors()).isEqualTo(100);
    }

    @Test
    public void testGetMostPopularFilms() {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setFilmId((short) 1);
        filmDTO.setTitle("Popular Film");

        when(dashboardService.getMostPopularFilms()).thenReturn(Arrays.asList(filmDTO));

        ResponseEntity<List<FilmDTO>> response = dashboardController.getMostPopularFilms();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()).isEqualTo(1);
        assertThat(response.getBody().get(0).getTitle()).isEqualTo("Popular Film");
    }
}
