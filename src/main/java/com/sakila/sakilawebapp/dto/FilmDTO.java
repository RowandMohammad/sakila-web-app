package com.sakila.sakilawebapp.dto;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class FilmDTO {
    private Short filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private Byte languageId;
    private Date lastUpdate = Date.valueOf(LocalDate.now());
}
