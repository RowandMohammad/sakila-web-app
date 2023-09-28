package com.sakila.sakilawebapp.service.implementation;

import com.sakila.sakilawebapp.dto.DashboardStats;
import com.sakila.sakilawebapp.dto.FilmDTO;
import com.sakila.sakilawebapp.repository.ActorRepository;
import com.sakila.sakilawebapp.repository.CategoryRepository;
import com.sakila.sakilawebapp.repository.FilmRepository;
import com.sakila.sakilawebapp.repository.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DashboardServiceImplTest {

    @InjectMocks
    private DashboardServiceImpl dashboardService;

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private LanguageRepository languageRepository;

    @Test
    void testGetDashboardStats() {
        when(actorRepository.count()).thenReturn(100L);
        when(filmRepository.count()).thenReturn(200L);
        when(categoryRepository.count()).thenReturn(10L);
        when(languageRepository.count()).thenReturn(5L);

        DashboardStats stats = dashboardService.getDashboardStats();

        assertThat(stats.getTotalActors()).isEqualTo(100);
        assertThat(stats.getTotalFilms()).isEqualTo(200);
    }

    @Test
    void testGetMostPopularFilms() {
        Object[] mockfilmRecord = new Object[] {(short) 1, "Popular Film"};
        when(filmRepository.findMostPopularFilms()).thenReturn(Collections.singletonList(mockfilmRecord));

        List<FilmDTO> films = dashboardService.getMostPopularFilms();

        assertThat(films).hasSize(1);
        assertThat(films.get(0).getTitle()).isEqualTo("Popular Film");
    }
}
