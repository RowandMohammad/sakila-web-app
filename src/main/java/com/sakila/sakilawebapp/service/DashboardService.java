package com.sakila.sakilawebapp.service;

import com.sakila.sakilawebapp.dto.DashboardStats;
import com.sakila.sakilawebapp.dto.FilmDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DashboardService {
    DashboardStats getDashboardStats();
    List<FilmDTO> getMostPopularFilms();

    }
