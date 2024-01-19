package com.sakila.sakilawebapp.controller;

import com.sakila.sakilawebapp.dto.DashboardStats;
import com.sakila.sakilawebapp.dto.FilmDTO;
import com.sakila.sakilawebapp.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStats> getDashboardStats() {
        DashboardStats stats = dashboardService.getDashboardStats();
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }

    @GetMapping("/popularFilms")
    public ResponseEntity<List<FilmDTO>> getMostPopularFilms() {
        List<FilmDTO> popularFilms = dashboardService.getMostPopularFilms();
        return new ResponseEntity<>(popularFilms, HttpStatus.OK);
    }
}
