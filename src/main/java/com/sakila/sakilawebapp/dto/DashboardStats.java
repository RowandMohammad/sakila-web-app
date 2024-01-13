package com.sakila.sakilawebapp.dto;

import lombok.Data;

@Data
public class DashboardStats {
    private int totalActors;
    private int totalFilms;
    private int totalCategories;
    private int totalLanguages;
}
