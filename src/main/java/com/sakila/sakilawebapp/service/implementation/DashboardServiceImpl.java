package com.sakila.sakilawebapp.service.implementation;

import com.sakila.sakilawebapp.dto.DashboardStats;
import com.sakila.sakilawebapp.dto.FilmDTO;
import com.sakila.sakilawebapp.repository.ActorRepository;
import com.sakila.sakilawebapp.repository.CategoryRepository;
import com.sakila.sakilawebapp.repository.FilmRepository;
import com.sakila.sakilawebapp.repository.LanguageRepository;
import com.sakila.sakilawebapp.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public DashboardStats getDashboardStats() {
        DashboardStats stats = new DashboardStats();
        stats.setTotalActors((int) actorRepository.count());
        stats.setTotalFilms((int) filmRepository.count());
        stats.setTotalCategories((int) categoryRepository.count());
        stats.setTotalLanguages((int) languageRepository.count());
        return stats;
    }

    @Override
    public List<FilmDTO> getMostPopularFilms() {
        List<Object[]> results = filmRepository.findMostPopularFilms();
        return results.stream().map(record -> {
            FilmDTO dto = new FilmDTO();
            dto.setFilmId(((Number) record[0]).shortValue());
            dto.setTitle((String) record[1]);
            return dto;
        }).collect(Collectors.toList());
    }



}
